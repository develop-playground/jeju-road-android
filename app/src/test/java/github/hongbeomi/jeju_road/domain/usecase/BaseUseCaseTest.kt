package github.hongbeomi.jeju_road.domain.usecase

import github.hongbeomi.jeju_road.util.MainCoroutineRule
import org.junit.Rule
import org.junit.Test

abstract class BaseUseCaseTest {

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    @Test
    abstract fun `실행 성공 테스트`()

    @Test
    abstract fun `실행 실패 테스트`()

}