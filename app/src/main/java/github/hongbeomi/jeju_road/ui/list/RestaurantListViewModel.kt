package github.hongbeomi.jeju_road.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import github.hongbeomi.jeju_road.domain.usecase.GetRestaurantListUseCase
import github.hongbeomi.jeju_road.util.UiState
import github.hongbeomi.jeju_road.util.toUiState

class RestaurantListViewModel(
    private val getRestaurantListUseCase: GetRestaurantListUseCase
) : ViewModel() {

    val restaurantList = liveData {
        emit(UiState.loading())
        emit(getRestaurantListUseCase.invoke().toUiState())
    }

}