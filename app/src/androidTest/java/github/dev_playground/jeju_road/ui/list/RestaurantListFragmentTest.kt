package github.dev_playground.jeju_road.ui.list


import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import github.dev_playground.jeju_road.BaseAndroidTest
import github.dev_playground.jeju_road.domain.model.Content
import github.dev_playground.jeju_road.domain.usecase.GetRestaurantListUseCase
import github.dev_playground.jeju_road.getOrAwaitValue
import github.dev_playground.jeju_road.presentation.ui.list.RestaurantListFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import github.dev_playground.jeju_road.presentation.ui.list.RestaurantListViewModel
import github.dev_playground.jeju_road.presentation.util.EspressoIdlingResource
import github.dev_playground.jeju_road.presentation.util.UiState
import github.dev_playground.jeju_road.ui.util.DataBindingIdlingResource
import org.junit.After
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.junit.Assert.assertEquals
import github.dev_playground.jeju_road.presentation.R
import github.dev_playground.jeju_road.ui.util.monitorFragment
import kotlinx.coroutines.delay

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class RestaurantListFragmentTest : BaseAndroidTest() {
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
    private val dataBindingIdlingResource = DataBindingIdlingResource()

    @Before
    fun setUp() {
        restaurantListViewModel = RestaurantListViewModel(getRestaurantListUseCase)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().register(dataBindingIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().unregister(dataBindingIdlingResource)
    }

    @Test
    fun `예기치_못한_에러가_터졌을때_에러페이지가_잘_나오는지에_대한_테스트`() = runBlockingTest {
        //given

        //when
        whenever(getRestaurantListUseCase.invoke(pageIndex))
            .thenReturn(Result.success(contentList))

        val scenario = launchFragmentInContainer<RestaurantListFragment>()

        dataBindingIdlingResource.monitorFragment(scenario)

        scenario.onFragment {
            restaurantListViewModel.refreshContentList()
        }

    }
}