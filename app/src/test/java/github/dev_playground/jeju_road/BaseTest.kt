package github.dev_playground.jeju_road

import github.dev_playground.jeju_road.presentation.util.MainCoroutineRule
import org.junit.Rule

abstract class BaseTest {

    @get:Rule
    var coroutineRule = MainCoroutineRule()


}