package github.dev_playground.jeju_road.presentation.viewmodel

import github.dev_playground.jeju_road.BaseTest
import github.dev_playground.jeju_road.domain.model.DayKor
import github.dev_playground.jeju_road.domain.model.DetailInformation
import github.dev_playground.jeju_road.domain.model.Menu
import github.dev_playground.jeju_road.domain.model.OpenTime
import github.dev_playground.jeju_road.domain.usecase.GetRestaurantDetailUseCase
import github.dev_playground.jeju_road.presentation.ui.page.RestaurantDetailViewModel
import github.dev_playground.jeju_road.presentation.util.UiState
import github.dev_playground.jeju_road.presentation.util.getOrAwaitValue
import github.dev_playground.jeju_road.runBlockingTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class RestaurantDetailViewModelTest : BaseTest() {

    private lateinit var getRestaurantPageUseCase: GetRestaurantDetailUseCase
    private lateinit var restaurantDetailViewModel: RestaurantDetailViewModel

    private val id: Long = 1L

    private val detailInformation =
        DetailInformation(
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
                    id = 1L,
                    day = DayKor.MON,
                    operationStart = "",
                    operationEnd = "",
                    breakStart = "???",
                    breakEnd = "???"
                )
            ),
            introduction = "소개글",
            tips = listOf("")
        )

    private val exception = NullPointerException()

    @Before
    fun setUp() {
        getRestaurantPageUseCase = mock()
        restaurantDetailViewModel = RestaurantDetailViewModel(id, getRestaurantPageUseCase)
    }

    @Test
    fun `상세 정보 가져올 때 로딩 및 데이터 상태 검증`() = coroutineRule.runBlockingTest {
        //when
        whenever(getRestaurantPageUseCase.invoke(id))
            .thenReturn(Result.success(detailInformation))

        // then -> loading
        assertTrue(restaurantDetailViewModel.detailInformationState.getOrAwaitValue().loading)

        // then
        assertEquals(
            restaurantDetailViewModel.detailInformationState.getOrAwaitValue(),
            UiState(data = detailInformation)
        )
    }

    @Test
    fun `상세 정보 가져올 때 에러 발생 시 상태 검증`() = coroutineRule.runBlockingTest {
        //when
        whenever(getRestaurantPageUseCase.invoke(any()))
            .thenReturn(Result.failure(exception))

        // then
        assertTrue(restaurantDetailViewModel.detailInformationState.getOrAwaitValue().loading)
        assertEquals(
            UiState<DetailInformation>(exception = exception),
            restaurantDetailViewModel.detailInformationState.getOrAwaitValue()
        )
    }

}