package github.dev_playground.jeju_road.domain.usecase

import github.dev_playground.jeju_road.data.model.DetailInformation
import github.dev_playground.jeju_road.data.model.Menu
import github.dev_playground.jeju_road.data.model.RestaurantDetail
import github.dev_playground.jeju_road.data.model.ServingTimeData
import github.dev_playground.jeju_road.data.repository.RestaurantRepository
import github.dev_playground.jeju_road.util.Result
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsInstanceOf
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetRestaurantDetailUseCaseTest : BaseUseCaseTest() {

    private val repository: RestaurantRepository = mock()
    private val id = 1L
    private val restaurantDetail = RestaurantDetail(
        message = "test",
        information = DetailInformation(
            id = 1L,
            name = "떡볶이",
            images = listOf("대충 이미지 URL"),
            menus = listOf(
                Menu(
                    id = 2L,
                    name = "아무튼 떡볶이",
                    image = "image url",
                    price = 9000
                )
            ),
            wayToGo = "한밭대에서 1분",
            simpleAddress = "한밭대학교 학하서로",
            detailAddress = "디테일",
            openTimes = listOf(
                ServingTimeData(
                    day = "??",
                    servingTime = "",
                    breakTime = "???"
                )
            ),
            introduction = "대충 소개글",
            tips = listOf("팁팁")
        )
    )

    @Test
    override fun `실행 성공 테스트`() = runBlocking {
        // given
        val useCase = GetRestaurantDetailUseCase(repository, coroutineRule.testDispatcher)
        whenever(repository.getRestaurantDetail(id))
            .thenReturn(restaurantDetail)

        // when
        val result = useCase.invoke(id)

        // then
        MatcherAssert.assertThat(
            result,
            CoreMatchers.`is`(IsInstanceOf(Result.Success::class.java))
        )

        val successResult = result as Result.Success

        Assert.assertEquals(1L, successResult.data.information.id)
        Assert.assertEquals("떡볶이", successResult.data.information.name)
    }

    @Test
    override fun `실행 실패 테스트`() = runBlocking {
        // given
        val useCase = GetRestaurantDetailUseCase(repository, coroutineRule.testDispatcher)

        whenever(repository.getRestaurantDetail(id))
            .thenThrow(IllegalStateException("Test"))

        // when
        val result = useCase.invoke(id)

        // then
        MatcherAssert.assertThat(result, CoreMatchers.`is`(IsInstanceOf(Result.Error::class.java)))

        val errorResult = result as Result.Error
        Assert.assertEquals("Test", errorResult.exception.message)
    }

}