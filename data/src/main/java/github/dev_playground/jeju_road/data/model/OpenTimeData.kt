package github.dev_playground.jeju_road.data.model

import github.dev_playground.jeju_road.domain.model.OpenTime

data class OpenTimeData(
    val day: String,
    val servingTime: String,
    val breakTime: String
)

fun OpenTimeData.toDomain() : OpenTime {
  return OpenTime(day, servingTime, breakTime)
}