package github.dev_playground.jeju_road.domain.usecase.model

import android.icu.util.Calendar
import github.dev_playground.jeju_road.domain.model.DayKor
import github.dev_playground.jeju_road.domain.model.OpenTime
import io.mockk.every
import io.mockk.mockkStatic
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.text.SimpleDateFormat
import java.util.*

class OpenTimeTest {

    private val openTime = OpenTime(
        0L,
        DayKor.WED,
        "",
        "",
        "",
        ""
    )

    @Test
    fun `현재_날짜의_요일을_제대로_계산하는지에_대한_검증_테스트`() {
        // given
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val date: Date? = formatter.parse("2022-07-13")

        val calendarMock = mock<Calendar>()
        mockkStatic(Calendar::class)

        // when
        every { Calendar.getInstance() } returns calendarMock
        whenever(calendarMock.time).thenReturn(date)

        //then
        assertEquals("수", openTime.currentDayOfWeek().dayOfWeek)
    }
}

