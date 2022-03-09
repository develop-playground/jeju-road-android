package github.dev_playground.jeju_road.data.model

import github.dev_playground.jeju_road.domain.model.Content

data class ContentData(
    val id: Long,
    val name: String,
    val category: List<String>,
    val address: String,
    val image: String? = null,
    val introduction: String
)

fun ContentData.toDomain() : Content {
  return Content(id,  name, category, address, image, introduction)
}