package github.dev_playground.jeju_road.domain.usecase.model

import github.dev_playground.jeju_road.domain.model.DayKor
import github.dev_playground.jeju_road.domain.model.OpenTime
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class OpenTimeTest {

    private lateinit var mockOpenTime: OpenTime

    @Before
    fun setUp() {
        mockOpenTime = mock()
    }

    @Test
    fun `현재_날짜의_요일을_제대로_계산하는지에_대한_검증_테스트`() {
        //given
        whenever(mockOpenTime.currentDayOfWeek())
            .thenReturn(DayKor.values()[3])

        val currentDayOfWeek = mockOpenTime.currentDayOfWeek().dayOfWeek

        //then
        assertEquals("수", currentDayOfWeek)
    }
}

