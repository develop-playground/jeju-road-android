package github.hongbeomi

import android.app.Application
import github.hongbeomi.jeju_road.di.dispatcherModule
import github.hongbeomi.jeju_road.di.networkModule
import github.hongbeomi.jeju_road.di.repositoryModule
import github.hongbeomi.jeju_road.di.useCaseModule
import org.koin.core.context.startKoin

class JejuRoadApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            listOf(
                dispatcherModule,
                networkModule,
                repositoryModule,
                useCaseModule
            )
        }
    }

}