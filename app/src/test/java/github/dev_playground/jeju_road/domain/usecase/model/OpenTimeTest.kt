package github.dev_playground.jeju_road.domain.usecase.model

import github.dev_playground.jeju_road.domain.model.DayKor
import github.dev_playground.jeju_road.domain.model.OpenTime
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.MockedStatic
import org.mockito.Mockito.mockStatic
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.util.*

class OpenTimeTest {

    private val openTimeData = OpenTime(
        0L,
        DayKor.WED,
        "",
        "",
        "",
        "",
    )

    private lateinit var instance: MockedStatic<Calendar>

    @After
    fun tearDown() {
        instance.close()
    }

    @Test
    fun `현재_날짜의_요일을_제대로_계산하는지에_대한_검증_테스트`() {

        // given
        instance = mockStatic(Calendar::class.java)
        val calenderMock = mock<Calendar>()

        // when
        whenever(Calendar.getInstance()).thenReturn(calenderMock)
        whenever(calenderMock.get(Calendar.DAY_OF_WEEK)).thenReturn(DAY_OF_WEEK_WEDNESDAY)

        // then
        assertEquals("수", openTimeData.currentDayOfWeek().dayOfWeek)
    }

    companion object {
        const val DAY_OF_WEEK_WEDNESDAY = 4
    }
}


