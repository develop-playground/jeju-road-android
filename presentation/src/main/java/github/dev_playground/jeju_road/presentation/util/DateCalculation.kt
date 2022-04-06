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
        2 -> "MON"
        3 -> "TUE"
        4 -> "WED"
        5 -> "THU"
        6 -> "FRI"
        7 -> "SAT"
        else -> "SUN"
    }
}