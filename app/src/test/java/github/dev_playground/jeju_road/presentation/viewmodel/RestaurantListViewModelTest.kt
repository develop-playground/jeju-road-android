package github.dev_playground.jeju_road.presentation.viewmodel

import com.example.test_util.runBlockingTest
import github.dev_playground.jeju_road.BaseTest
import github.dev_playground.jeju_road.domain.model.Content
import github.dev_playground.jeju_road.domain.usecase.GetRestaurantListUseCase
import github.dev_playground.jeju_road.presentation.ui.list.RestaurantListViewModel
import github.dev_playground.jeju_road.presentation.util.UiState
import com.example.test_util.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
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
            image = "이미지 URL",
            introduction = "소개글"
        )
    )

    private val exception = NullPointerException()

    @Before
    fun setUp() {
        restaurantListViewModel = RestaurantListViewModel(getRestaurantListUseCase)
    }

    @Test
    fun `새로고침 이후 데이터 상태 검증`() = coroutineRule.runBlockingTest {
        whenever(getRestaurantListUseCase.invoke(pageIndex))
            .thenReturn(Result.success(contentList))

        //when
        pauseDispatcher()
        restaurantListViewModel.refreshContentList()

        //then
        assertEquals(
            UiState.loading<List<Content>>(),
            restaurantListViewModel.contentListState.getOrAwaitValue()
        )
        resumeDispatcher()

        assertEquals(
            UiState(data = contentList),
            restaurantListViewModel.contentListState.getOrAwaitValue()
        )
    }

    @Test
    fun `새로고침 이후 빈 리스트를 받을 경우 데이터 상태 검증`() = coroutineRule.runBlockingTest {
        whenever(getRestaurantListUseCase.invoke(any()))
            .thenReturn(Result.success(emptyList()))

        pauseDispatcher()
        restaurantListViewModel.refreshContentList()

        assertEquals(
            UiState.loading<List<Content>>(),
            restaurantListViewModel.contentListState.getOrAwaitValue()
        )
        resumeDispatcher()

        assertEquals(
            UiState<List<Content>>(data = emptyList()),
            restaurantListViewModel.contentListState.getOrAwaitValue()
        )
    }

    @Test
    fun `새로고침 이후 예외 발생 시 상태 검증`() = coroutineRule.runBlockingTest {
        whenever(getRestaurantListUseCase.invoke(pageIndex))
            .thenReturn(Result.failure(exception))

        //when
        restaurantListViewModel.refreshContentList()

        //then
        assertEquals(
            UiState<List<Content>>(exception = exception),
            restaurantListViewModel.contentListState.getOrAwaitValue()
        )
    }

}