package github.dev_playground.jeju_road.presentation.viewmodel

import github.dev_playground.jeju_road.BaseTest
import github.dev_playground.jeju_road.domain.model.Content
import github.dev_playground.jeju_road.domain.usecase.GetRestaurantListUseCase
import github.dev_playground.jeju_road.presentation.ui.list.RestaurantListViewModel
import github.dev_playground.jeju_road.presentation.util.UiState
import github.dev_playground.jeju_road.presentation.util.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class RestaurantListViewModelTest : BaseTest() {
    private lateinit var restaurantListViewModel: RestaurantListViewModel
    private val getRestaurantListUseCase: GetRestaurantListUseCase = mock()
    private val pageIndex = 0
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

    @Before
    fun setUp() {
        restaurantListViewModel = RestaurantListViewModel(getRestaurantListUseCase)
    }

    @Test
    fun `새로고침하면 로딩 표시 후 콘텐츠 리스트가 잘 표시되는지 검증`() = runBlockingTest {
        whenever(getRestaurantListUseCase.invoke(pageIndex))
            .thenReturn(Result.success(contentList))

        //when
        coroutineRule.pauseDispatcher()
        restaurantListViewModel.refreshContentList()

        //then
        assertEquals(
            restaurantListViewModel.contentListState.getOrAwaitValue(),
            UiState.loading<List<Content>>()
        )
        coroutineRule.resumeDispatcher()
        assertEquals(
            restaurantListViewModel.contentListState.getOrAwaitValue(),
            UiState(data = contentList)
        )
    }

    @Test
    fun `새로고침 실패 검증`() = runBlocking {
        whenever(getRestaurantListUseCase.invoke(pageIndex))
            .thenThrow(IllegalStateException("Failed to fetch information."))

        //when
        restaurantListViewModel.refreshContentList()

        //then
    }
}