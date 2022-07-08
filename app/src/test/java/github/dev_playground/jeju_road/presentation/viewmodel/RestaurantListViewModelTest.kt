package github.dev_playground.jeju_road.presentation.viewmodel

import github.dev_playground.jeju_road.BaseTest
import github.dev_playground.jeju_road.domain.model.Content
import github.dev_playground.jeju_road.domain.usecase.GetRestaurantListUseCase
import github.dev_playground.jeju_road.presentation.ui.list.RestaurantListViewModel
import github.dev_playground.jeju_road.presentation.util.UiState
import github.dev_playground.jeju_road.presentation.util.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
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
    fun `새로고침하면_로딩_표시_후_콘텐츠_리스트가_잘_표시되는지_검증`() = runBlockingTest {
        whenever(getRestaurantListUseCase.invoke(pageIndex))
            .thenReturn(Result.success(contentList))

        //when
        coroutineRule.testDispatcher.pauseDispatcher()
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
    fun `새로고침_했을때_예외가_생길때_실패_검증`() = runBlockingTest {
        whenever(getRestaurantListUseCase.invoke(pageIndex))
            .thenReturn(Result.failure(exception))


        //when
        restaurantListViewModel.refreshContentList()

        //then
        assertThat(
            restaurantListViewModel.contentListState.getOrAwaitValue(),
            `is`(UiState<List<Content>>(exception = exception))
        )
    }
}