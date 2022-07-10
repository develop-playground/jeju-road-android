package github.dev_playground.jeju_road.domain.model

data class DetailInformation(
    val id: Long,
    val name: String,
    val images: List<String>? = null,
    val menus: List<Menu>,
    val wayToGo: String,
    val simpleAddress: String,
    val detailAddress: String,
    val openTimes: List<OpenTime>,
    val introduction: String,
    val tips: List<String>,
)
