package github.dev_playground.jeju_road.presentation.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import github.dev_playground.jeju_road.domain.model.Content
import github.dev_playground.jeju_road.presentation.R
import github.dev_playground.jeju_road.presentation.databinding.ItemRestaurantListBinding
import github.dev_playground.jeju_road.presentation.ui.base.BaseListAdapter
import github.dev_playground.jeju_road.presentation.ui.component.RestaurantCategoryView
import github.dev_playground.jeju_road.presentation.util.RoundRectOutlineProvider

class RestaurantListAdapter(
    private val onItemClick: (Content, View) -> Unit
) : BaseListAdapter<Content>(DIFF_CALLBACK) {

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
                onItemClick.invoke(getItem(bindingAdapterPosition), binding.constraintLayoutItemRestaurantListArea)
            }
        }

        override fun bind(data: Content) {
            with(binding) {
                content = data
                linearLayoutItemRestaurantListCategory.removeAllViews()
                data.getCategoryList().forEachIndexed { index, s ->
                    addCategoryView(index, s)
                }
                ViewCompat.setTransitionName(constraintLayoutItemRestaurantListArea, data.name)
                executePendingBindings()
            }
        }

        private fun addCategoryView(index: Int, title: String) {
            val categoryView = RestaurantCategoryView(binding.root.context).apply {
                if (index != 0) {
                    val margin = binding.root.context.resources.getDimensionPixelSize(R.dimen.dp_4)
                    val params = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    params.leftMargin = margin
                    layoutParams = params
                }
                text = title
            }
            binding.linearLayoutItemRestaurantListCategory.addView(categoryView)
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
