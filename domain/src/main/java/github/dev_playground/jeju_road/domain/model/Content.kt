package github.dev_playground.jeju_road.domain.model

data class Content(
    val id: Long,
    val name: String,
    val categories: List<String>,
    val address: String,
    val image: String?,
    val introduction: String
) {

    fun getCategoryList(): List<String> {
        return categories + address
    }

}