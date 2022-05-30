package github.dev_playground.jeju_road.presentation.viewmodel

import github.dev_playground.jeju_road.BaseTest
import github.dev_playground.jeju_road.domain.model.Content
import github.dev_playground.jeju_road.domain.usecase.GetRestaurantListUseCase
import github.dev_playground.jeju_road.presentation.ui.list.RestaurantListViewModel
import github.dev_playground.jeju_road.presentation.util.UiState
import github.dev_playground.jeju_road.presentation.util.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.junit.Assert.assertEquals

@ExperimentalCoroutinesApi
class RestaurantListViewModelTest: BaseTest() {
    private lateinit var restaurantListViewModel: RestaurantListViewModel
    private val getRestaurantListUseCase: GetRestaurantListUseCase = mock()

    @Before
    fun setUp() {
        restaurantListViewModel = RestaurantListViewModel(getRestaurantListUseCase)
    }

    @Test
    fun `리스트 새로고침`() {
        //when
        coroutineRule.pauseDispatcher()
        restaurantListViewModel.refreshContentList()

        //then
        assertEquals(
            restaurantListViewModel.contentListState.getOrAwaitValue()
            , UiState<List<Content>>().loading
        )
    }
}