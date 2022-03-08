package github.dev_playground.jeju_road.data.model

import github.dev_playground.jeju_road.domain.model.DetailInformation

data class DetailInformationData(
    val id: Long,
    val name: String,
    val images: List<String>,
    val menus: List<MenuData>,
    val howToGo: String,
    val address: String,
    val servingTime: List<ServingTimeData>,
    val introduction: String,
    val tips: List<String>
)

fun DetailInformationData.toDomain(): DetailInformation {
    return DetailInformation(
        id,
        name,
        images,
        menus.map { it.toDomain() },
        howToGo,
        address,
        servingTime.map { it.toDomain() },
        introduction,
        tips
    )
}