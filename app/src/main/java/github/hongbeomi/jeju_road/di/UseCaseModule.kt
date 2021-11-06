package github.hongbeomi.jeju_road.di

import github.hongbeomi.jeju_road.domain.usecase.GetRestaurantListUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetRestaurantListUseCase(get(), get(named(IO))) }
}