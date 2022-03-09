package github.dev_playground.jeju_road.domain.usecase

import github.dev_playground.jeju_road.data.model.OpenTimeData
import github.dev_playground.jeju_road.domain.model.DetailInformation
import github.dev_playground.jeju_road.domain.model.Menu
import github.dev_playground.jeju_road.domain.model.OpenTime
import github.dev_playground.jeju_road.domain.repository.RestaurantRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetRestaurantDetailUseCaseTest : BaseUseCaseTest() {

    private val repository: RestaurantRepository = mock()
    private val id = 1L

    private val detailInformation = DetailInformation(
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
            OpenTime(
                day = "??",
                servingTime = "",
                breakTime = "???"
            )
        ),
        introduction = "대충 소개글",
        tips = listOf("팁팁")
    )

    @Test
    override fun `실행 성공 테스트`() = runBlocking {
        // given
        val useCase = GetRestaurantDetailUseCase(repository, coroutineRule.testDispatcher)

        // when
        whenever(repository.getRestaurantDetail(id))
            .thenReturn(detailInformation)

        val result = useCase.invoke(id)

        // then
        Assert.assertEquals(true, result.isSuccess)

        Assert.assertEquals(1L, result.getOrNull()?.id)
        Assert.assertEquals("떡볶이", result.getOrNull()?.name)
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
        Assert.assertEquals(true, result.isFailure)
        Assert.assertEquals("Test", result.exceptionOrNull()?.message)
    }

}