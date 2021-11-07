package github.hongbeomi

import android.app.Application
import github.hongbeomi.jeju_road.di.*
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