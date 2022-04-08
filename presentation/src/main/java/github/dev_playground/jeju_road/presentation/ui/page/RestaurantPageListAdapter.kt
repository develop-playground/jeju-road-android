package github.dev_playground.jeju_road.presentation.ui.page

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import github.dev_playground.jeju_road.domain.model.DetailInformation
import github.dev_playground.jeju_road.presentation.R
import github.dev_playground.jeju_road.presentation.ui.component.RestaurantIntroductionView

class RestaurantPageListAdapter: RecyclerView.Adapter<RestaurantPageListAdapter.PageViewHolder>() {

    private var detailInformation: DetailInformation? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        return when(viewType) {
            R.layout.view_restaurant_introduction -> ContentViewHolder(RestaurantIntroductionView(parent.context))
            else -> InformationViewHolder(TextView(parent.context))
        }
    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        detailInformation?.let {
            holder.bind(it)
        }
    }

    override fun getItemCount() = 2

    override fun getItemViewType(position: Int): Int {
        return when(position) {
            0 -> R.layout.view_restaurant_introduction
            else -> R.layout.view_restaurant_default_information
        }
    }

    fun setDetailInformation(detailInformation: DetailInformation) {
        this.detailInformation = detailInformation
        notifyDataSetChanged()
    }

    abstract class PageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(data: DetailInformation)
    }

    inner class ContentViewHolder(
        val view: RestaurantIntroductionView
    ): PageViewHolder(view) {
        override fun bind(data: DetailInformation) {
            view.setContentInformation(data)
        }
    }

    inner class InformationViewHolder(
        val view: TextView
    ): PageViewHolder(view) {
        override fun bind(data: DetailInformation) {
//            view.text = "${data.simpleAddress}\n${data.detailAddress}"
        }
    }

}