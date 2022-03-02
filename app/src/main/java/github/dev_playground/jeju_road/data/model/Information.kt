package github.dev_playground.jeju_road.data.model

import java.io.Serializable

data class Information(
    val id: Long,
    val name: String,
    val category: List<String>,
    val address: String,
    val image: String,
    val introduction: String
) : Serializable {

    fun formatCategoryAndAddress(): String {
        return formatCategory() + " · " + address
    }

    private fun formatCategory(): String {
        return category.joinToString(separator = "/")
    }

}