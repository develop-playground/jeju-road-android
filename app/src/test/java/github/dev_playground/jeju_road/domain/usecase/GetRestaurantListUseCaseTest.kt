package github.dev_playground.jeju_road.domain.usecase

import github.dev_playground.jeju_road.domain.model.Information
import github.dev_playground.jeju_road.domain.repository.RestaurantRepository
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetRestaurantListUseCaseTest : BaseUseCaseTest() {

    private val repository: RestaurantRepository = mock()
    private val informationList = listOf(
        Information(
            id = 1,
            name = "맛집",
            category = listOf("category"),
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
        whenever(repository.getRestaurantList())
            .thenReturn(informationList)

        // when
        val result = useCase.invoke()

        // then
        assertEquals(true, result.isSuccess)

        assertEquals(1, result.getOrNull()?.informationList?.size)

        assertEquals(1L, result.getOrNull()?.informationList?.get(0)?.id)
        assertEquals("맛집", result.getOrNull()?.informationList?.get(0)?.name)
    }

    @Test
    override fun `실행 실패 테스트`() = runBlocking {
        // given
        val useCase = GetRestaurantListUseCase(
            repository,
            coroutineRule.testDispatcher
        )

        whenever(repository.getRestaurantList())
            .thenThrow(IllegalStateException("Test"))

        // when
        val result = useCase.invoke()

        // then
        assertEquals(true, result.isFailure)

        assertEquals("Test", result.exceptionOrNull()?.message)
    }

}