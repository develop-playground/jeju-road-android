package github.dev_playground.jeju_road.domain.usecase

import github.dev_playground.jeju_road.domain.model.Content
import github.dev_playground.jeju_road.domain.model.Information
import github.dev_playground.jeju_road.domain.repository.RestaurantRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetRestaurantListUseCaseTest : BaseUseCaseTest() {

    private val repository: RestaurantRepository = mock()
    private var pageIndex = 0

    private val contentList = listOf(
        Content(
            id = 1,
            name = "맛집",
            categories = listOf("category"),
            address = "한밭대학교",
            image = "대충 이미지 URL",
            introduction = "대충 소개글"
        )
    )

    @Test
    override fun `실행 성공 테스트`() = runBlocking {
        // given
        val useCase = GetRestaurantListUseCase(
            repository,
            coroutineRule.testDispatcher
        )

        // when
        whenever(repository.getRestaurantList(pageIndex))
            .thenReturn(contentList)

        val result = useCase.invoke(pageIndex)

        // then
        assertEquals(true, result.isSuccess)

        assertEquals(1, result.getOrNull()?.size)

        assertEquals(1L, result.getOrNull()?.get(0)?.id)
        assertEquals("맛집", result.getOrNull()?.get(0)?.name)
    }

    @Test
    override fun `실행 실패 테스트`() = runBlocking {
        // given
        val useCase = GetRestaurantListUseCase(
            repository,
            coroutineRule.testDispatcher
        )


        // when
        whenever(repository.getRestaurantList(pageIndex))
            .thenThrow(IllegalStateException("Test"))

        val result = useCase.invoke(pageIndex)

        // then
        assertEquals(true, result.isFailure)

        assertEquals("Test", result.exceptionOrNull()?.message)
    }

}