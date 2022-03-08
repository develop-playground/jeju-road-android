package github.dev_playground.jeju_road.domain.usecase

<<<<<<< HEAD
import github.dev_playground.jeju_road.domain.model.Information

=======
import github.dev_playground.jeju_road.data.model.Restaurants

>>>>>>> 6e4a09ce803e496b9c26f06b8a62b56b649fbe86
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetRestaurantListUseCaseTest : BaseUseCaseTest() {

<<<<<<< HEAD
    private val repository: RestaurantRepository = mock()
    private val informationList = listOf(
        Information(
            id = 1,
            name = "맛집",
            category = listOf("category"),
            address = "한밭대학교",
            image = "대충 이미지 URL",
            introduction = "대충 소개글"
=======
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
>>>>>>> 6e4a09ce803e496b9c26f06b8a62b56b649fbe86
        )

    )

    @Test
    override fun `실행 성공 테스트`() = runBlocking {
        // given
<<<<<<< HEAD
        val useCase = GetRestaurantListUseCase(
            repository,
            coroutineRule.testDispatcher
        )
        whenever(repository.getRestaurantList())
            .thenReturn(informationList)
=======
        val useCase = GetRestaurantListUseCase(repository, coroutineRule.testDispatcher)
>>>>>>> 6e4a09ce803e496b9c26f06b8a62b56b649fbe86

        // when
        whenever(repository.getRestaurantList(pageIndex))
            .thenReturn(restaurantData)

        val result = useCase.invoke(pageIndex)

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


        // when
        whenever(repository.getRestaurantList(pageIndex))
            .thenThrow(IllegalStateException("Test"))

        val result = useCase.invoke(pageIndex)

        // then
        assertEquals(true, result.isFailure)

        assertEquals("Test", result.exceptionOrNull()?.message)
    }

}