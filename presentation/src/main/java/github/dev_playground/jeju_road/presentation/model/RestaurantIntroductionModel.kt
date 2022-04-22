package github.dev_playground.jeju_road.presentation.model

import github.dev_playground.jeju_road.domain.model.DetailInformation
import github.dev_playground.jeju_road.domain.model.Menu

data class RestaurantIntroductionModel(
    override val id: Long,
    val name: String,
    val images: List<String>? = null,
    val introduction: String,
    val menus: List<Menu>
): RestaurantInformationModel {

    companion object {
        fun toPresentation(information: DetailInformation): RestaurantIntroductionModel {
            return information.run {
                RestaurantIntroductionModel(
                    id,
                    name,
                    images,
                    introduction,
                    menus,
                )
            }
        }
    }

}