package github.dev_playground.jeju_road.data.model

import github.dev_playground.jeju_road.domain.model.Content

internal data class ContentData(
    val id: Long,
    val name: String,
    val categories: List<String>,
    val address: String,
    val image: String? = null,
    val introduction: String
)

internal fun ContentData.toDomain() : Content {
  return Content(id,  name, categories, address, image, introduction)
}