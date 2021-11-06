package github.hongbeomi.jeju_road

import github.hongbeomi.jeju_road.util.MainCoroutineRule
import org.junit.Rule

abstract class BaseTest {

    @get:Rule
    var coroutineRule = MainCoroutineRule()


}