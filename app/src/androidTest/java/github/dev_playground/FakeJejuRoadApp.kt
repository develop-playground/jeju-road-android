package github.dev_playground

import androidx.test.core.app.ApplicationProvider
import github.dev_playground.jeju_road.presentation.util.handler.GlobalErrorExceptionHandler

class FakeJejuRoadApp : JejuRoadApp() {
    override fun onCreate() {
        super.onCreate()
        setCrashHandler()
    }

    override fun setCrashHandler() {
        Thread.setDefaultUncaughtExceptionHandler(
            GlobalErrorExceptionHandler(
                ApplicationProvider.getApplicationContext()
            )
        )
    }
}