package github.dev_playground.jeju_road.data.model

import github.dev_playground.jeju_road.domain.model.DetailInformation

data class DetailInformationData(
    val id: Long,
    val name: String,
    val images: List<String>? = null,
    val menus: List<MenuData>,
    val wayToGo: String,
    val simpleAddress: String,
    val detailAddress: String,
    val openTimes: List<OpenTimeData>,
    val introduction: String,
    val tips: List<String>
)

fun DetailInformationData.toDomain(): DetailInformation {
    return DetailInformation(
        id,
        name,
        images,
        menus.map { it.toDomain() },
        wayToGo,
        simpleAddress,
        detailAddress,
        openTimes.map { it.toDomain() },
        introduction,
        tips
    )
}