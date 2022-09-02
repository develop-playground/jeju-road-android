package github.dev_playground.jeju_road.presentation.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.TextViewCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import github.dev_playground.jeju_road.domain.model.OpenTime
import github.dev_playground.jeju_road.presentation.R
import github.dev_playground.jeju_road.presentation.databinding.ItemRestaurantDefaultInformationServingTimeBinding
import github.dev_playground.jeju_road.presentation.databinding.ItemRestaurantDefaultInformationTipBinding
import github.dev_playground.jeju_road.presentation.databinding.ViewRestaurantDefaultInformationBinding
import github.dev_playground.jeju_road.presentation.model.RestaurantDetailInformationModel
import github.dev_playground.jeju_road.presentation.ui.base.BaseListAdapter

class RestaurantDefaultInformationView
@JvmOverloads
constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : BaseCustomView<ViewRestaurantDefaultInformationBinding>(context, attr, defStyleAttr) {

    override fun getLayoutId() = R.layout.view_restaurant_default_information

    fun setDetailInformation(detailInformation: RestaurantDetailInformationModel) {
        setAddress(detailInformation.detailAddress)
        setWayToGo(detailInformation.wayToGo)
        setOpenTimes(detailInformation.openTimes)
        setTipList(detailInformation.tips)
    }

    private fun setAddress(address: String) {
        binding.textViewRestaurantDefaultInformationAddress.text = address
    }

    private fun setWayToGo(wayToGo: String) {
        binding.textViewRestaurantDefaultInformationHowToGo.text = wayToGo
    }

    private fun setOpenTimes(servingTime: List<OpenTime>) {
        with(binding) {
            isExpand = false
            servingTime.find { it.day == it.currentDayOfWeek() }?.let {
                textViewRestaurantDefaultInformationServingTime.text = resources.getString(
                    R.string.text_restaurant_default_information_serving_time_format,
                    it.day.dayOfWeek,
                    it.servingTime,
                )
            }
            val adapter = ContentOpenTimesListAdapter()

            toggleButtonRestaurantDefaultInformationFlip.setOnCheckedChangeListener { _, checked ->
                if (checked) {
                    isExpand = false
                } else {
                    isExpand = true
                    recyclerViewRestaurantDefaultInformationServingTime.adapter = adapter.apply {
                        submitList(servingTime)
                    }
                }
            }
        }
    }

    private fun setTipList(tips: List<String>?) {
        binding.recyclerViewRestaurantDefaultInformationTip.adapter =
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
                    R.layout.item_restaurant_default_information_serving_time,
                    parent,
                    false
                )
            )
        }

        private inner class OpenTimeViewHolder(val binding: ItemRestaurantDefaultInformationServingTimeBinding) :
            BaseViewHolder(binding.root) {

            override fun bind(data: OpenTime) {
                with(binding) {
                    openTime = data
                    if (openTime?.day == openTime?.currentDayOfWeek()) {
                        TextViewCompat.setTextAppearance(
                            textViewItemRestaurantDefaultInformationDay,
                            R.style.JejuLoadTextStyle_Focus
                        )
                        TextViewCompat.setTextAppearance(
                            textViewItemRestaurantDefaultInformationServingTime,
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
                    R.layout.item_restaurant_default_information_tip,
                    parent,
                    false
                )
            )
        }

        private inner class TipsPagerViewHolder(val binding: ItemRestaurantDefaultInformationTipBinding) :
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