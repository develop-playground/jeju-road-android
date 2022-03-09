package github.dev_playground.jeju_road.domain.model

data class Restaurant(
    val message: String,
    val information: Information
)

fun Restaurant?.isNotNullOrEmpty(): Boolean {
    return this != null && information.contentList.isNotEmpty()
}
