package github.dev_playground.jeju_road.domain.usecase.model

import github.dev_playground.jeju_road.domain.model.Menu
import org.junit.Assert.assertEquals
import org.junit.Test

class MenuTest {

    private val menu = Menu(
        id = 1L,
        name = "",
        image = "",
        price = 44000
    )

    @Test
    fun `가격_포멧이_잘_변경되는지에_대한_테스트`() {
        //given
        val expectFormatPrice = "44,000"

        //when
        val actualFormatPrice = menu.formatPrice()

        //then
        assertEquals(expectFormatPrice, actualFormatPrice)
    }
}