package github.hongbeomi.jeju_road.di

import github.hongbeomi.jeju_road.ui.list.RestaurantListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { RestaurantListViewModel(get()) }
}