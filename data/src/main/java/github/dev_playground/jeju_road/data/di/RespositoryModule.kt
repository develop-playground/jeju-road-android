package github.dev_playground.jeju_road.data.di

import github.dev_playground.jeju_road.data.repository.RestaurantRepositoryImpl
import github.dev_playground.jeju_road.domain.repository.RestaurantRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<RestaurantRepository> {
        RestaurantRepositoryImpl(get())
    }
}
