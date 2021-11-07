package github.hongbeomi.jeju_road.domain.model

data class Information(
    val id: Long,
    val name: String,
    val category: List<String>,
    val address: String,
    val image: String,
    val introduction: String
)
