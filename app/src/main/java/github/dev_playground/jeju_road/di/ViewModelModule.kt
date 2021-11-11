package github.dev_playground.jeju_road.di

import github.dev_playground.jeju_road.ui.list.RestaurantListViewModel
import github.dev_playground.jeju_road.ui.loading.LoadingEventViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { RestaurantListViewModel(get()) }
    viewModel { LoadingEventViewModel() }
}