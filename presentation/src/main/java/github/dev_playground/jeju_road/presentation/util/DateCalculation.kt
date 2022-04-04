package github.dev_playground.jeju_road.presentation.util

import github.dev_playground.jeju_road.domain.model.DayKor
import github.dev_playground.jeju_road.domain.model.OpenTime
import java.text.SimpleDateFormat
import java.util.*

fun currentDayOfWeek(): String {
    val date = Date(System.currentTimeMillis())
    val cal = Calendar.getInstance()
    cal.time = date

    return when(cal.get(Calendar.DAY_OF_WEEK)) {
        0 -> "MON"
        1 -> "TUE"
        2 -> "WED"
        3 -> "THU"
        4 -> "FRI"
        5 -> "SAT"
        else -> "SUN"
    }
}