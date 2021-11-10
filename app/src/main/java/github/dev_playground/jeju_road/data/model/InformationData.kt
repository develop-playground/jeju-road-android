package github.dev_playground.jeju_road.data.model

data class InformationData(
    val id: Long,
    val name: String,
    val category: List<String>,
    val address: String,
    val image: String,
    val introduction: String
)