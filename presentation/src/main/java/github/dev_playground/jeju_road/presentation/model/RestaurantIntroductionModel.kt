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
            return RestaurantIntroductionModel(
                information.id,
                information.name,
                information.images,
                information.introduction,
                information.menus,
            )
        }
    }

}