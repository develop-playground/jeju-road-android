package github.dev_playground.jeju_road.data.model

data class DetailInformationData (
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