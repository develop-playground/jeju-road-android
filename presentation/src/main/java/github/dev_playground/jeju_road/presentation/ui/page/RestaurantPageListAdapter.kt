package github.dev_playground.jeju_road.presentation.ui.page

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import github.dev_playground.jeju_road.domain.model.DetailInformation
import github.dev_playground.jeju_road.presentation.R
import github.dev_playground.jeju_road.presentation.model.RestaurantDetailInformationModel
import github.dev_playground.jeju_road.presentation.model.RestaurantInformationModel
import github.dev_playground.jeju_road.presentation.model.RestaurantIntroductionModel
import github.dev_playground.jeju_road.presentation.ui.component.RestaurantDefaultInformationView
import github.dev_playground.jeju_road.presentation.ui.component.RestaurantIntroductionView

class RestaurantPageListAdapter : RecyclerView.Adapter<RestaurantPageListAdapter.PageViewHolder>() {

    private var itemList: List<RestaurantInformationModel> = listOf()

    fun submitList(newItemList: List<RestaurantInformationModel>) {
        itemList = newItemList
        notifyItemRangeChanged(0, itemList.size - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        return when (viewType) {
            R.layout.view_restaurant_introduction -> IntroductionViewHolder(
                RestaurantIntroductionView(parent.context)
            )
            else -> DetailInformationViewHolder(
                RestaurantDefaultInformationView(parent.context)
            )
        }
    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount() = itemList.size

    override fun getItemViewType(position: Int): Int {
        return when (itemList[position]) {
            is RestaurantIntroductionModel -> R.layout.view_restaurant_introduction
            else -> R.layout.view_restaurant_default_information
        }
    }

    abstract class PageViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        abstract fun <T : RestaurantInformationModel> bind(model: T)
    }

    inner class IntroductionViewHolder(val view: RestaurantIntroductionView) :
        PageViewHolder(view) {
        override fun <T : RestaurantInformationModel> bind(model: T) {
            view.setIntroduction(model as RestaurantIntroductionModel)
        }
    }

    inner class DetailInformationViewHolder(
        val view: RestaurantDefaultInformationView
    ) : PageViewHolder(view) {
        override fun <T : RestaurantInformationModel> bind(model: T) {
            view.setDetailInformation(model as RestaurantDetailInformationModel)
        }
    }

}