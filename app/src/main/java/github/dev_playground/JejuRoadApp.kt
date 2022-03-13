package github.dev_playground

import android.app.Application
import github.dev_playground.jeju_road.data.di.networkModule
import github.dev_playground.jeju_road.data.di.repositoryModule
import github.dev_playground.jeju_road.domain.di.dispatcherModule
import github.dev_playground.jeju_road.domain.di.useCaseModule
import github.dev_playground.jeju_road.presentation.di.appModule
import github.dev_playground.jeju_road.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class JejuRoadApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                appModule,
                dispatcherModule,
                networkModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
            androidContext(this@JejuRoadApp)
        }
    }

}