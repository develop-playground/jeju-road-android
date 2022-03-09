package github.dev_playground.jeju_road.domain.model

data class DetailInformation(
    val id: Long,
    val name: String,
    val images: List<String>,
    val menus: List<Menu>,
    val howToGo: String,
    val address: String,
    val servingTime: List<ServingTime>,
    val introduction: String,
    val tips: List<String>
)
