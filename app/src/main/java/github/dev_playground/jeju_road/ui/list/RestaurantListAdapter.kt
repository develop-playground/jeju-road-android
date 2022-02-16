package github.dev_playground.jeju_road.ui.list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import github.dev_playground.jeju_road.R
import github.dev_playground.jeju_road.databinding.ItemRestaurantListBinding
import github.dev_playground.jeju_road.domain.model.Information
import github.dev_playground.jeju_road.ui.base.BaseListAdapter
import github.dev_playground.jeju_road.util.RoundRectOutlineProvider
import github.dev_playground.jeju_road.util.dataBinding

class RestaurantListAdapter : BaseListAdapter<Information>(
    R.layout.item_restaurant_list,
    DIFF_CALLBACK
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return ViewHolder(parent.dataBinding(ItemRestaurantListBinding::inflate))
    }

    inner class ViewHolder(private val binding: ItemRestaurantListBinding) : BaseViewHolder(binding.root) {
        override fun bind(data: Information) {
            binding.apply {
                information = data
                imageViewItemRestaurantListImage.outlineProvider = RoundRectOutlineProvider(
                    root.resources.getDimension(R.dimen.dp_8)
                )
                executePendingBindings()
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Information>() {
            override fun areItemsTheSame(oldItem: Information, newItem: Information) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Information, newItem: Information) = oldItem == newItem
        }
    }

}