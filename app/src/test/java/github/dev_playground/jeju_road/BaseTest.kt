package github.dev_playground.jeju_road

import org.junit.Rule

abstract class BaseTest {

    @get:Rule
    var coroutineRule = MainCoroutineRule()


}