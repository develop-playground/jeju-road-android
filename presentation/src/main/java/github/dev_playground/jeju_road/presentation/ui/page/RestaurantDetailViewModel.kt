package github.dev_playground.jeju_road.presentation.ui.page

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import github.dev_playground.jeju_road.domain.model.DetailInformation
import github.dev_playground.jeju_road.domain.usecase.GetRestaurantDetailUseCase
import github.dev_playground.jeju_road.presentation.util.UiState
import github.dev_playground.jeju_road.presentation.util.toUiState

class RestaurantDetailViewModel(
    id: Long,
    private val getRestaurantPageUseCase: GetRestaurantDetailUseCase
) : ViewModel() {

    val detailInformationState: LiveData<UiState<DetailInformation>> =
        liveData {
            emit(UiState.loading())
            emit(getRestaurantPageUseCase.invoke(id).toUiState())
        }
}