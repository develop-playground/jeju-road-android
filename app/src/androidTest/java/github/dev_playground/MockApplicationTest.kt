package github.dev_playground

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import github.dev_playground.jeju_road.presentation.ui.main.MainActivity
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MockApplicationTest {

    @get:Rule
    val activityTestRule : ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Throws(Exception::class)
    @Test
    fun testApplicationName() {
        assertEquals(
            "FakeJejuRoadApp",
            activityTestRule.activity.application.javaClass.simpleName)
    }

}