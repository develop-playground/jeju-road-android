package github.dev_playground.jeju_road.di

import github.dev_playground.jeju_road.data.repository.RestaurantRepository
import github.dev_playground.jeju_road.data.repository.RestaurantRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<RestaurantRepository> { RestaurantRepositoryImpl(get()) }
}
