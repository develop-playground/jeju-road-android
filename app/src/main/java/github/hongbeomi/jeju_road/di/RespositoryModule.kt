package github.hongbeomi.jeju_road.di

import github.hongbeomi.jeju_road.data.repository.RestaurantRepository
import github.hongbeomi.jeju_road.data.repository.RestaurantRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<RestaurantRepository> { RestaurantRepositoryImpl(get()) }
}
