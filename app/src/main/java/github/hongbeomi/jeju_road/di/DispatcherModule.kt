package github.hongbeomi.jeju_road.di

import github.hongbeomi.jeju_road.util.CoroutinesDispatcherProvider
import org.koin.dsl.module

val dispatcherModule = module {
    factory { CoroutinesDispatcherProvider() }
}