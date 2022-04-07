package github.dev_playground.jeju_road.presentation.ui.page

import androidx.lifecycle.*
import github.dev_playground.jeju_road.domain.model.DetailInformation
import github.dev_playground.jeju_road.domain.usecase.GetRestaurantDetailUseCase
import github.dev_playground.jeju_road.presentation.util.UiState
import github.dev_playground.jeju_road.presentation.util.toUiState
import kotlinx.coroutines.*

class RestaurantPageViewModel(
    private val getRestaurantPageUseCase: GetRestaurantDetailUseCase
) : ViewModel() {

    val id: MutableLiveData<Long> = MutableLiveData<Long>()

    val detailInformationState: LiveData<UiState<DetailInformation>> = id.switchMap {
        liveData {
            emit(UiState.loading())
            emit(getRestaurantPageUseCase.invoke(it).toUiState())
        }
    }
}