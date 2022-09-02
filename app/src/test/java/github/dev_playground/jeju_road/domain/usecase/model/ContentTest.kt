package github.dev_playground.jeju_road.domain.usecase.model

import github.dev_playground.jeju_road.domain.model.Content
import org.junit.Assert.assertEquals
import org.junit.Test

class ContentTest {

    private val content = Content(
        1L,
        name = "",
        categories = listOf(
            "피시방",
            "음식점"
        ),
        address = "한밭대학교",
        image = "",
        introduction = "",
    )

    @Test
    fun `카테고리_리스트가_올바르게_완성되는지에_대한_테스트`() {
        //given
        val expectedCategoryList = listOf(
            "피시방", "음식점", "한밭대학교"
        )

        //when
        val actualCategoryList = content.getCategoryList()

        //then
        assertEquals(expectedCategoryList, actualCategoryList)
    }
}