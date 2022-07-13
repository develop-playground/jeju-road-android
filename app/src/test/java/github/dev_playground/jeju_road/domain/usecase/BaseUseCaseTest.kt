package github.dev_playground.jeju_road.domain.usecase

import github.dev_playground.jeju_road.BaseTest
import org.junit.Test

abstract class BaseUseCaseTest: BaseTest() {

    @Test
    abstract fun `실행 성공 테스트`()

    @Test
    abstract fun `실행 실패 테스트`()
}