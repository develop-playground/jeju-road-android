package github.dev_playground

import android.app.Application
import github.dev_playground.jeju_road.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class JejuRoadApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
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