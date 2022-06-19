package github.dev_playground.jeju_road.ui.list


import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import github.dev_playground.jeju_road.BaseAndroidTest
import github.dev_playground.jeju_road.presentation.ui.list.RestaurantListFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.runner.RunWith
import github.dev_playground.jeju_road.presentation.util.EspressoIdlingResource
import github.dev_playground.jeju_road.ui.util.DataBindingIdlingResource
import github.dev_playground.jeju_road.presentation.R
import github.dev_playground.jeju_road.presentation.ui.page.RestaurantDetailActivity
import github.dev_playground.jeju_road.ui.util.monitorFragment
import org.junit.*
import org.junit.Assert.assertEquals
import org.koin.core.component.getScopeName

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class RestaurantListFragmentTest : BaseAndroidTest() {

    private val dataBindingIdlingResource = DataBindingIdlingResource()

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().register(dataBindingIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().unregister(dataBindingIdlingResource)
    }

    @Test(expected = RuntimeException::class)
    fun `예기치_못한_에러가_터졌을때_에러페이지가_잘_나오는지에_대한_테스트`() = runBlockingTest {
        //given
        val scenario = launchFragmentInContainer<RestaurantListFragment>()
        dataBindingIdlingResource.monitorFragment(scenario)

        //when
        //첫번째 아이템 눌럿을 때 디테일 페이지로 넘어가기
        onView(withId(R.id.recyclerView_restaurant_list))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0, click()
                )
            )

        //STARTED일 때 runtime exception
        val activityScenario = ActivityScenario.launch(RestaurantDetailActivity::class.java)
        activityScenario.apply {
            moveToState(Lifecycle.State.CREATED).onActivity {
                throw RuntimeException()
            }
        }
    }
}