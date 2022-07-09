package github.dev_playground.jeju_road.presentation.ui.page

import android.view.ViewGroup
import github.dev_playground.jeju_road.presentation.R
import github.dev_playground.jeju_road.presentation.model.RestaurantDetailInformationModel
import github.dev_playground.jeju_road.presentation.model.RestaurantInformationModel
import github.dev_playground.jeju_road.presentation.model.RestaurantIntroductionModel
import github.dev_playground.jeju_road.presentation.ui.base.BaseListAdapter
import github.dev_playground.jeju_road.presentation.ui.component.RestaurantDefaultInformationView
import github.dev_playground.jeju_road.presentation.ui.component.RestaurantIntroductionView
import github.dev_playground.jeju_road.presentation.util.SimpleItemDiffCallback

class RestaurantDetailListAdapter : BaseListAdapter<RestaurantInformationModel>(
    SimpleItemDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            R.layout.view_restaurant_introduction -> IntroductionViewHolder(
                RestaurantIntroductionView(parent.context)
            )
            else -> DetailInformationViewHolder(
                RestaurantDefaultInformationView(parent.context)
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (currentList[position]) {
            is RestaurantIntroductionModel -> R.layout.view_restaurant_introduction
            else -> R.layout.view_restaurant_default_information
        }
    }

    inner class IntroductionViewHolder(val view: RestaurantIntroductionView) :
        BaseViewHolder(view) {
        override fun bind(data: RestaurantInformationModel) {
            view.setIntroduction(data as RestaurantIntroductionModel)
        }
    }

    inner class DetailInformationViewHolder(
        val view: RestaurantDefaultInformationView,
    ) : BaseViewHolder(view) {
        override fun bind(data: RestaurantInformationModel) {
            view.setDetailInformation(data as RestaurantDetailInformationModel)
        }
    }

}