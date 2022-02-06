package github.dev_playground.jeju_road.di

import github.dev_playground.jeju_road.domain.usecase.GetRestaurantDetailUseCase
import github.dev_playground.jeju_road.domain.usecase.GetRestaurantListUseCase
import github.dev_playground.jeju_road.domain.usecase.GetRestaurantPagingUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetRestaurantListUseCase(get(), get(named(IO))) }
    factory { GetRestaurantDetailUseCase(get(), get(named(IO))) }
    factory { GetRestaurantPagingUseCase(get(), get(named(IO))) }
}