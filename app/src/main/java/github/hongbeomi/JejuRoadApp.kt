package github.hongbeomi

import android.app.Application
import org.koin.core.context.startKoin

class JejuRoadApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {

        }
    }

}