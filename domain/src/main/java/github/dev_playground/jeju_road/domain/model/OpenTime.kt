package github.dev_playground.jeju_road.domain.model

import java.util.*

data class OpenTime(
    val id: Long,
    val day: DayKor,
    val servingTime: String,
    val breakTime: String,
) {

    fun currentDayOfWeek(): DayKor {
        val cal = Calendar.getInstance()
        val date = Date(System.currentTimeMillis())
        cal.time = date
        val dayOfWeekIndex = cal.get(Calendar.DAY_OF_WEEK)

        return DayKor.values()[dayOfWeekIndex - 1]
    }
}

enum class DayKor(val dayOfWeek: String) {
    SUN(dayOfWeek = "일"),
    MON(dayOfWeek = "월"),
    TUE(dayOfWeek = "화"),
    WED(dayOfWeek = "수"),
    THU(dayOfWeek = "목"),
    FRI(dayOfWeek = "금"),
    SAT(dayOfWeek = "토"),
}
