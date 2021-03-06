package github.dev_playground.jeju_road.data.model

import github.dev_playground.jeju_road.domain.model.Menu

internal data class MenuData(
    val id: Long,
    val name: String,
    val image: String,
    val price: Long
)

internal fun MenuData.toDomain() : Menu {
  return Menu(id, name, image, price)
}