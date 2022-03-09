package github.dev_playground.jeju_road.di

import github.dev_playground.jeju_road.ui.list.RestaurantListViewModel
import github.dev_playground.jeju_road.ui.loading.LoadingEventViewModel
import github.dev_playground.jeju_road.ui.page.RestaurantPageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { RestaurantListViewModel(getRestaurantListUseCase = get())}
    viewModel { RestaurantPageViewModel(get()) }
    viewModel { LoadingEventViewModel() }
}