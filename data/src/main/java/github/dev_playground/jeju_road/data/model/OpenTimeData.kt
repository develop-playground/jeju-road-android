package github.dev_playground.jeju_road.data.model

import github.dev_playground.jeju_road.domain.model.DayKor
import github.dev_playground.jeju_road.domain.model.OpenTime

internal data class OpenTimeData(
    val id: Long,
    val day: DayKor,
    val operationStart: String,
    val operationEnd: String,
    val breakStart: String,
    val breakEnd: String
)

internal fun OpenTimeData.toDomain() : OpenTime {
  return OpenTime(id, day, operationStart, operationEnd, breakStart, breakEnd)
}