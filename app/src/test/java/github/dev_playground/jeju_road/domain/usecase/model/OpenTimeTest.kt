package github.dev_playground.jeju_road.domain.usecase.model

import github.dev_playground.jeju_road.domain.model.DayKor
import github.dev_playground.jeju_road.domain.model.OpenTime
import org.junit.Assert.assertEquals
import org.junit.Test

class OpenTimeTest {

    private val openTime = listOf(
        OpenTime(
            id = 1L,
            day = DayKor.MON,
            operationStart = "",
            operationEnd = "",
            breakStart = "",
            breakEnd = "",
        ),
        OpenTime(
            id = 1L,
            day = DayKor.TUE,
            operationStart = "",
            operationEnd = "",
            breakStart = "",
            breakEnd = "",
        ),
        OpenTime(
            id = 1L,
            day = DayKor.WED,
            operationStart = "",
            operationEnd = "",
            breakStart = "",
            breakEnd = "",
        ),
        OpenTime(
            id = 1L,
            day = DayKor.THU,
            operationStart = "",
            operationEnd = "",
            breakStart = "",
            breakEnd = "",
        ),
        OpenTime(
            id = 1L,
            day = DayKor.FRI,
            operationStart = "",
            operationEnd = "",
            breakStart = "",
            breakEnd = "",
        ),
        OpenTime(
            id = 1L,
            day = DayKor.SAT,
            operationStart = "",
            operationEnd = "",
            breakStart = "",
            breakEnd = "",
        ),
        OpenTime(
            id = 1L,
            day = DayKor.SUN,
            operationStart = "",
            operationEnd = "",
            breakStart = "",
            breakEnd = "",
        )
    )

    @Test
    fun `현재_날짜의_요일을_제대로_계산하는지에_대한_검증_테스트`() {

        //when
        val isDayOfWeek = openTime.any {
            it.day.dayOfWeek == it.currentDayOfWeek().dayOfWeek
        }

        //then
        assertEquals(true, isDayOfWeek)
    }
}