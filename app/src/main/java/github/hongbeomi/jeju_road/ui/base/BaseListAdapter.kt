package github.hongbeomi.jeju_road.ui.base

import android.view.View
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseListAdapter<ITEM>(
    @LayoutRes
    private val itemLayoutId: Int,
    diffCallback: DiffUtil.ItemCallback<ITEM>
) : ListAdapter<ITEM, BaseListAdapter<ITEM>.BaseViewHolder>(diffCallback) {

    override fun getItemViewType(position: Int) = itemLayoutId

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    abstract inner class BaseViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {
        abstract fun bind(data: ITEM)
    }

}