package github.dev_playground

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import github.dev_playground.jeju_road.data.di.networkModule
import github.dev_playground.jeju_road.data.di.repositoryModule
import github.dev_playground.jeju_road.di.appModule
import github.dev_playground.jeju_road.domain.di.dispatcherModule
import github.dev_playground.jeju_road.domain.di.useCaseModule
import github.dev_playground.jeju_road.presentation.di.viewModelModule
import github.dev_playground.jeju_road.presentation.util.handler.GlobalErrorExceptionHandler
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

open class JejuRoadApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Logger.addLogAdapter(AndroidLogAdapter())
        startKoin {
            modules(
                appModule,
                dispatcherModule,
                networkModule,
                repositoryModule,
                useCaseModule,
                viewModelModule,
            )
            androidContext(this@JejuRoadApp)
        }

        setCrashHandler()
    }

    protected open fun setCrashHandler() {
        Thread.setDefaultUncaughtExceptionHandler(
            GlobalErrorExceptionHandler(
                this
            )
        )
    }
}