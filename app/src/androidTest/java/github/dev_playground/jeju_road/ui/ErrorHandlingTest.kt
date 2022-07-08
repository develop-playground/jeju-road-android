package github.dev_playground.jeju_road.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import github.dev_playground.jeju_road.BaseAndroidTest
import org.junit.runner.RunWith
import github.dev_playground.jeju_road.presentation.util.EspressoIdlingResource
import github.dev_playground.jeju_road.ui.util.DataBindingIdlingResource
import github.dev_playground.jeju_road.presentation.R
import github.dev_playground.jeju_road.presentation.ui.main.MainActivity
import github.dev_playground.jeju_road.ui.util.monitorActivity
import org.junit.*

@RunWith(AndroidJUnit4::class)
class ErrorHandlingTest : BaseAndroidTest() {

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

    @Test
    fun `예기치_못한_에러가_상세페이지에서_발생했을때_에러페이지가_잘_나오는지에_대한_테스트`() {
        //when
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)

        //then
        onView(withId(R.id.recyclerView_restaurant_list))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0, click()
                )
            )

        onView(withId(R.id.errorView_restaurant_detail)).check(matches(isDisplayed()))

        activityScenario.close()
    }

    @Test
    fun `예기치_못한_에러가_리스트페이지에서_발생했을때_에러페이지가_잘_나오는지에_대한_테스트`() {

        //when
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)

        //then
        onView(withId(R.id.errorView_restaurant_list)).check(matches(isDisplayed()))

        activityScenario.close()
    }

    @Test
    fun `에러페이지_새로고침_버튼을_눌렀을때_해당_페이지가_새로고침_되는지에_대한_테스트`() {
        //given

        //when
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)

        //then
        onView(withId(R.id.errorView_restaurant_list)).check(matches(isDisplayed()))
        onView(withId(R.id.button_retry_custom_error_view)).perform(click())
        onView(withId(R.id.recyclerView_restaurant_list)).check(matches(isDisplayed()))

        activityScenario.close()
    }

}