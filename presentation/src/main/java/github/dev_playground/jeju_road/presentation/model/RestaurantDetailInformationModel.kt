package github.dev_playground.jeju_road.presentation.model

import github.dev_playground.jeju_road.domain.model.DetailInformation
import github.dev_playground.jeju_road.domain.model.OpenTime

data class RestaurantDetailInformationModel(
    override val id: Long,
    val wayToGo: String,
    val simpleAddress: String,
    val detailAddress: String,
    val openTimes: List<OpenTime>,
    val tips: List<String>
) : RestaurantInformationModel {

    companion object {
        fun toPresentation(information: DetailInformation): RestaurantDetailInformationModel {
            return information.run {
                RestaurantDetailInformationModel(
                    id,
                    wayToGo,
                    simpleAddress,
                    detailAddress,
                    openTimes,
                    tips,
                )
            }
        }
    }

}