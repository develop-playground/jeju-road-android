package github.dev_playground.jeju_road

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import github.dev_playground.jeju_road.ui.util.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

@ExperimentalCoroutinesApi
abstract class BaseAndroidTest {

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
}