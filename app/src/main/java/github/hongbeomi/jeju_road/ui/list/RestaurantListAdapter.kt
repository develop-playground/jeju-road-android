package github.hongbeomi.jeju_road.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.doOnLayout
import androidx.core.view.doOnNextLayout
import androidx.core.view.updateLayoutParams
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import github.hongbeomi.jeju_road.R
import github.hongbeomi.jeju_road.databinding.ItemRestaurantListBinding
import github.hongbeomi.jeju_road.domain.model.Information
import github.hongbeomi.jeju_road.ui.base.BaseListAdapter

class RestaurantListAdapter : BaseListAdapter<Information>(
    R.layout.item_restaurant_list,
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

    inner class ViewHolder(private val binding: ItemRestaurantListBinding) : BaseViewHolder(binding.root) {
        override fun bind(data: Information) {
            binding.apply {
                information = data
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