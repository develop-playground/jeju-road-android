package github.dev_playground.jeju_road

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import github.dev_playground.jeju_road.test_module.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

@ExperimentalCoroutinesApi
abstract class BaseTest {

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

}