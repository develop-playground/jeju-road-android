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
import github.dev_playground.jeju_road.presentation.util.toUiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
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
            tips = listOf(
                ""
            )

    )

    @Before
    fun setUp() {
        getRestaurantPageUseCase = mock()
        restaurantDetailViewModel = RestaurantDetailViewModel(getRestaurantPageUseCase)
    }

    @Test
    fun `상세 정보 데이터를 가져올 때 로딩 후 상세 정보 데이터가 잘 들어왔는지 검증`() = runBlocking {
        //when
        restaurantDetailViewModel.id.value = id

        whenever(getRestaurantPageUseCase.invoke(id))
            .thenReturn(Result.success(detailInformation))

        //then -> loading
        assertTrue(restaurantDetailViewModel.detailInformationState.getOrAwaitValue().loading)

        //then
        assertEquals(
            restaurantDetailViewModel.detailInformationState.getOrAwaitValue(),
            UiState(data = detailInformation)
        )
    }
}