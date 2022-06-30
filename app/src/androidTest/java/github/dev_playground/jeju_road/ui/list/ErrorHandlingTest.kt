package github.dev_playground.jeju_road.ui.list

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
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
    fun `예기치_못한_에러가_터졌을때_에러페이지가_잘_나오는지에_대한_테스트`() {

        //given
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)

        //when
        onView(withId(R.id.recyclerView_restaurant_list))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0, click()
                )
            )

//        onView(withId(R.id.constraintLayout_global_error)).check(ViewAssertions.matches(isDisplayed()))

        //then
        activityScenario.close()
    }

}