package github.dev_playground.jeju_road.domain.usecase

import github.dev_playground.jeju_road.data.model.Information
import github.dev_playground.jeju_road.data.model.Restaurants
import github.dev_playground.jeju_road.data.repository.RestaurantRepository
import github.dev_playground.jeju_road.util.Result
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsInstanceOf
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetRestaurantListUseCaseTest: BaseUseCaseTest() {

    private val repository : RestaurantRepository = mock()
    private var pageIndex = 0
    private val restaurantData = Restaurants(
        message = "test",
        informationList = listOf(
            Information(
                id = 1,
                name = "맛집",
                category = listOf("category"),
                address = "한밭대학교",
                image = "대충 이미지 URL",
                introduction = "대충 소개글"
            )
        )
    )

    @Test
    override fun `실행 성공 테스트`() = runBlocking {
        // given
        val useCase = GetRestaurantListUseCase(repository, coroutineRule.testDispatcher)

        // when
        whenever(repository.getRestaurantList(pageIndex))
            .thenReturn(restaurantData)

        val result = useCase.invoke(pageIndex)

        // then
        assertThat(result, `is`(IsInstanceOf(Result.Success::class.java)))

        val successResult  = result as Result.Success

        assertEquals(1, successResult.data.informationList.size)

        assertEquals(1L, successResult.data.informationList[0].id)
        assertEquals("맛집", successResult.data.informationList[0].name)
    }

    @Test
    override fun `실행 실패 테스트`() = runBlocking {
        // given
        val useCase = GetRestaurantListUseCase(repository, coroutineRule.testDispatcher)


        // when
        whenever(repository.getRestaurantList(pageIndex))
            .thenThrow(IllegalStateException("Test"))

        val result = useCase.invoke(pageIndex)

        // then
        assertThat(result, `is`(IsInstanceOf(Result.Error::class.java)))

        val errorResult  = result as Result.Error
        assertEquals("Test", errorResult.exception.message)
    }

}