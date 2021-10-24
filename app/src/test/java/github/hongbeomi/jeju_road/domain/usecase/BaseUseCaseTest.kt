package github.hongbeomi.jeju_road.domain.usecase

import github.hongbeomi.jeju_road.util.MainCoroutineRule
import org.junit.Rule

abstract class BaseUseCaseTest {

    @get:Rule
    var coroutineRule = MainCoroutineRule()

}