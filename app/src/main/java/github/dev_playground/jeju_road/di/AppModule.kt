package github.dev_playground.jeju_road.di

import github.dev_playground.jeju_road.BuildConfig
import github.dev_playground.jeju_road.data.di.BASE_URL_KEY
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single(qualifier = named(BASE_URL_KEY)) { BuildConfig.BASE_URL }
}