package github.dev_playground.jeju_road.presentation.ui.base

import android.view.View
import androidx.core.view.doOnAttach
import androidx.core.view.doOnDetach
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseListAdapter<ITEM>(
    diffCallback: DiffUtil.ItemCallback<ITEM>
) : ListAdapter<ITEM, BaseListAdapter<ITEM>.BaseViewHolder>(diffCallback) {

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    abstract inner class BaseViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {

        protected var lifecycleOwner: LifecycleOwner? = null

        init {
            rootView.doOnAttach {
                lifecycleOwner = it.findViewTreeLifecycleOwner()
            }
            rootView.doOnDetach {
                lifecycleOwner = null
            }
        }

        abstract fun bind(data: ITEM)
    }

}