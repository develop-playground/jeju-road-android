package github.hongbeomi.jeju_road

import org.junit.Rule

abstract class BaseAndroidTest<T> {

    @get:Rule
    var activityIntentRule = IntentsTestRule(FindpwActivity::class.java)

}