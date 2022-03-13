package github.dev_playground.jeju_road.presentation.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import github.dev_playground.jeju_road.domain.model.Content
import github.dev_playground.jeju_road.presentation.R
import github.dev_playground.jeju_road.presentation.databinding.ItemRestaurantListBinding
import github.dev_playground.jeju_road.presentation.ui.base.BaseListAdapter
import github.dev_playground.jeju_road.presentation.util.RoundRectOutlineProvider

class RestaurantListAdapter(private val viewModel: RestaurantListViewModel) : BaseListAdapter<Content>(
    DIFF_CALLBACK
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_restaurant_list,
                parent,
                false
            )
        )
    }

    inner class ViewHolder(private val binding: ItemRestaurantListBinding) :
        BaseViewHolder(binding.root) {

        init {
            binding.constraintLayoutItemRestaurantListArea.setOnClickListener {
                getItem(bindingAdapterPosition)?.let {
                    viewModel.callOnRestaurantClickEvent(it)
                }
            }
        }

        override fun bind(data: Content) {
            binding.apply {
                content = data
                imageViewItemRestaurantListImage.outlineProvider = RoundRectOutlineProvider()
                executePendingBindings()
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Content>() {
            override fun areItemsTheSame(oldItem: Content, newItem: Content) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Content, newItem: Content) =
                oldItem == newItem
        }
    }
}
