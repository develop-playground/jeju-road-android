package github.dev_playground.jeju_road.domain.model

import java.io.Serializable

data class Content(
    val id: Long,
    val name: String,
    val categories: List<String>,
    val address: String,
    val image: String?,
    val introduction: String
) : Serializable {

    fun formatCategoryAndAddress(): String {
        return formatCategory() + " · " + address
    }

    private fun formatCategory(): String {
        return categories.joinToString(separator = "/")
    }

}