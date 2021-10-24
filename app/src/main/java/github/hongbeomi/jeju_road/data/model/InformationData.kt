package github.hongbeomi.jeju_road.data.model

data class InformationData(
    val id: String,
    val name: String,
    val category: List<String>,
    val address: String,
    val image: String,
    val introduction: String
)