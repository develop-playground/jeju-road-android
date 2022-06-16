package github.dev_playground.jeju_road.ui.list

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import github.dev_playground.jeju_road.presentation.ui.list.RestaurantListFragment
import github.dev_playground.jeju_road.ui.util.DataBindingIdlingResource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class RestaurantListFragmentTest {
    private val dataBindingIdlingResource = DataBindingIdlingResource()

    @Before
    fun setUp() {

    }

    @Test
    fun `예기치_못한_에러가_터졌을때_에러페이지가_잘_나오는지에_대한_테스트`() = runBlockingTest {
        //given

        //when
        val fragmentArgs = bundleOf("test" to 0)
        launchFragmentInContainer<RestaurantListFragment>(fragmentArgs)

        //then
    }
}