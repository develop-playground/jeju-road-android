package github.dev_playground.jeju_road.presentation.viewmodel

import github.dev_playground.jeju_road.BaseTest
import github.dev_playground.jeju_road.domain.model.Content
import github.dev_playground.jeju_road.domain.usecase.GetRestaurantListUseCase
import github.dev_playground.jeju_road.presentation.ui.list.RestaurantListViewModel
import github.dev_playground.jeju_road.presentation.util.UiState
import github.dev_playground.jeju_road.presentation.util.getOrAwaitValue
import github.dev_playground.jeju_road.presentation.util.toUiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.junit.Assert.assertEquals
import org.mockito.kotlin.notNull
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class RestaurantListViewModelTest : BaseTest() {
    private lateinit var restaurantListViewModel: RestaurantListViewModel
    private val getRestaurantListUseCase: GetRestaurantListUseCase = mock()
    private val pageIndex = 0
    private val uiStateList = UiState(
        loading = false,
        exception = null,
        listOf(
            Content(
                id = 1,
                name = "맛집",
                categories = listOf("category"),
                address = "한밭대학교",
                image = "대충 이미지 URL",
                introduction = "대충 소개글"
            )
        )
    )

    @Before
    fun setUp() {
        restaurantListViewModel = RestaurantListViewModel(getRestaurantListUseCase)
    }

    @Test
    fun `리스트 새로고침 성공`() = runBlocking {
        whenever(getRestaurantListUseCase.invoke(pageIndex).toUiState())
            .thenReturn(uiStateList)

        val result = getRestaurantListUseCase.invoke(pageIndex).toUiState()

        //when
        coroutineRule.pauseDispatcher()
        restaurantListViewModel.refreshContentList()

        //then
        assertEquals(
            restaurantListViewModel.contentListState.getOrAwaitValue(),
            UiState<List<Content>>(data = emptyList())
        )

        assertEquals(
            restaurantListViewModel.recyclerState.getOrAwaitValue(),
            null
        )

        coroutineRule.resumeDispatcher()

        assertEquals(
            restaurantListViewModel.contentListState.getOrAwaitValue(),
            result
        )
    }

    @Test
    fun `리스트 새로고침 실패`() {
        //when
        coroutineRule.pauseDispatcher()
        restaurantListViewModel.refreshContentList()

        //then

    }
}