package github.dev_playground.jeju_road.presentation.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.TextViewCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import github.dev_playground.jeju_road.domain.model.DetailInformation
import github.dev_playground.jeju_road.domain.model.OpenTime
import github.dev_playground.jeju_road.presentation.R
import github.dev_playground.jeju_road.presentation.databinding.ItemRestaurantContentServingTimeBinding
import github.dev_playground.jeju_road.presentation.databinding.ItemRestaurantContentTipBinding
import github.dev_playground.jeju_road.presentation.databinding.ViewRestaurantInformationBinding
import github.dev_playground.jeju_road.presentation.ui.base.BaseListAdapter
import github.dev_playground.jeju_road.presentation.util.currentDayOfWeek

class RestaurantInformationView
@JvmOverloads
constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BaseCustomView<ViewRestaurantInformationBinding>(context, attr, defStyleAttr) {

    override fun getLayoutId() = R.layout.view_restaurant_information


    fun setRestaurantInformation(information: DetailInformation) {
        setAddress(information.detailAddress)
        setWayToGo(information.wayToGo)
        setOpenTimes(information.openTimes)
        setTipList(information.tips)
    }

    private fun setAddress(address: String) {
        binding.textViewRestaurantContentInformationAddress.text = address
    }

    private fun setWayToGo(wayToGo: String) {
        binding.textViewRestaurantContentInformationHowToGo.text = wayToGo
    }

    private fun setOpenTimes(servingTime: List<OpenTime>) {
        with(binding) {
            isExpand = false
            servingTime.find { it.day == currentDayOfWeek() }?.let {
                textViewRestaurantContentInformationServingTime.text =
                    "오늘" + "[${it.convertDayOfWeek(it.day)}] " + it.operationStart.substring(
                        0,
                        5
                    ) + "  -  " + it.operationEnd.substring(0, 5)
            }

            toggleButtonRestaurantContentInformationFlip.setOnCheckedChangeListener { _, checked ->
                if (checked) {
                    isExpand = false
                } else {
                    isExpand = true
                    recyclerViewRestaurantContentInformationServingTime.adapter =
                        ContentOpenTimesListAdapter().apply {
                            submitList(servingTime)
                        }
                }
            }
        }
    }

    private fun setTipList(tips: List<String>?) {
        binding.recyclerViewRestaurantContentInformationTip.adapter =
            ContentTipsListAdapter().apply {
                submitList(tips ?: emptyList())
            }
    }

    private inner class ContentOpenTimesListAdapter :
        BaseListAdapter<OpenTime>(OPEN_TIME_DIFF_CALLBACK) {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
            return OpenTimeViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_restaurant_content_serving_time,
                    parent,
                    false
                )
            )
        }

        private inner class OpenTimeViewHolder(val binding: ItemRestaurantContentServingTimeBinding) :
            BaseViewHolder(binding.root) {
            override fun bind(data: OpenTime) {
                with(binding) {
                    openTime = data
                    if (openTime?.day == currentDayOfWeek()) {
                        TextViewCompat.setTextAppearance(
                            binding.textViewItemRestaurantContentDay,
                            R.style.JejuLoadTextStyle_Focus
                        )
                        TextViewCompat.setTextAppearance(
                            binding.textViewItemRestaurantContentStartTime,
                            R.style.JejuLoadTextStyle_Focus
                        )
                        TextViewCompat.setTextAppearance(
                            binding.textViewItemRestaurantContentMiddleLine,
                            R.style.JejuLoadTextStyle_Focus
                        )
                        TextViewCompat.setTextAppearance(
                            binding.textViewItemRestaurantContentEndTime,
                            R.style.JejuLoadTextStyle_Focus
                        )
                    }
                    executePendingBindings()
                }
            }
        }
    }

    private inner class ContentTipsListAdapter : BaseListAdapter<String>(TIPS_DIFF_CALLBACK) {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
            return TipsPagerViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_restaurant_content_tip,
                    parent,
                    false
                )
            )
        }

        private inner class TipsPagerViewHolder(val binding: ItemRestaurantContentTipBinding) :
            BaseViewHolder(binding.root) {

            override fun bind(data: String) {
                with(binding) {
                    tip = data
                    executePendingBindings()
                }
            }
        }
    }

    companion object {
        private val OPEN_TIME_DIFF_CALLBACK = object : DiffUtil.ItemCallback<OpenTime>() {
            override fun areItemsTheSame(oldItem: OpenTime, newItem: OpenTime) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: OpenTime, newItem: OpenTime) =
                oldItem == newItem

        }

        private val TIPS_DIFF_CALLBACK = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String) = oldItem == newItem
            override fun areContentsTheSame(oldItem: String, newItem: String) = oldItem == newItem
        }
    }

}