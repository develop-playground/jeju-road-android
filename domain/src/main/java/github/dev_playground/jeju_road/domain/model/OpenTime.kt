package github.dev_playground.jeju_road.domain.model

data class OpenTime(
    val id: Long,
    val day: String,
    val operationStart: String,
    val operationEnd: String,
    val breakStart: String,
    val breakEnd: String
) {
    fun convertDayOfWeek(dayOfWeek: String): String {
        return when(dayOfWeek) {
            "MON" -> DayKor.MON.day
            "TUE" -> DayKor.TUE.day
            "WED" -> DayKor.WED.day
            "THU" -> DayKor.THU.day
            "FRI" -> DayKor.FRI.day
            "SAT" -> DayKor.SAT.day
            else -> DayKor.SUN.day
        }
    }
}

enum class DayKor(val day: String) {
    MON(day = "월"),
    TUE(day = "화"),
    WED(day = "수"),
    THU(day = "목"),
    FRI(day = "금"),
    SAT(day = "토"),
    SUN(day = "일"),
}
