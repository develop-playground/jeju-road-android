package github.dev_playground.jeju_road.data.model

import github.dev_playground.jeju_road.domain.model.ServingTime

data class ServingTimeData(
    val dayOfWeek: String,
    val serving: String
)

fun ServingTimeData.toDomain() : ServingTime {
  return ServingTime(dayOfWeek, serving)
}