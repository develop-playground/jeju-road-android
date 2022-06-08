package github.dev_playground.jeju_road.presentation.di

import github.dev_playground.jeju_road.presentation.ui.list.RestaurantListViewModel
import github.dev_playground.jeju_road.presentation.ui.page.RestaurantDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { RestaurantListViewModel(getRestaurantListUseCase = get())}
    viewModel { (id: Long) -> RestaurantDetailViewModel(id, get()) }
}