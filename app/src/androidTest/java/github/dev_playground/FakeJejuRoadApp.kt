package github.dev_playground

import android.app.Application
import github.dev_playground.jeju_road.data.FakeRestaurantRepositoryImpl
import github.dev_playground.jeju_road.domain.di.dispatcherModule
import github.dev_playground.jeju_road.domain.di.useCaseModule
import github.dev_playground.jeju_road.domain.repository.RestaurantRepository
import github.dev_playground.jeju_road.presentation.di.viewModelModule
import github.dev_playground.jeju_road.presentation.util.handler.GlobalErrorExceptionHandler
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class FakeJejuRoadApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                viewModelModule,
                useCaseModule,
                dispatcherModule,
                module {
                    single<RestaurantRepository> { FakeRestaurantRepositoryImpl() }
                }
            )
            androidContext(this@FakeJejuRoadApp)
        }

        setCrashHandler()
    }

    private fun setCrashHandler() {
        Thread.setDefaultUncaughtExceptionHandler(
            GlobalErrorExceptionHandler(
                this
            )
        )
    }
}