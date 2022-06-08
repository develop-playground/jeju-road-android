package github.dev_playground.jeju_road.presentation.ui.page

import androidx.lifecycle.*
import github.dev_playground.jeju_road.domain.usecase.GetRestaurantDetailUseCase
import github.dev_playground.jeju_road.presentation.model.RestaurantDetailInformationModel
import github.dev_playground.jeju_road.presentation.model.RestaurantInformationModel
import github.dev_playground.jeju_road.presentation.model.RestaurantIntroductionModel

class RestaurantDetailViewModel(
    private val getRestaurantPageUseCase: GetRestaurantDetailUseCase
) : ViewModel() {

    val id: MutableLiveData<Long> = MutableLiveData<Long>()

    val detailInformationState: LiveData<RestaurantDetailUiState> = id.switchMap {
        liveData {
            emit(RestaurantDetailUiState.Loading)
            
            val result = getRestaurantPageUseCase.invoke(it)
            result.onSuccess {
                RestaurantDetailUiState.Success(
                    listOf(
                        RestaurantIntroductionModel.toPresentation(it),
                        RestaurantDetailInformationModel.toPresentation(it)
                    )
                )
            }.onFailure {
                RestaurantDetailUiState.Error(it)
            }
        }
    }
}

sealed interface RestaurantDetailUiState {
    data class Success(val informationList: List<RestaurantInformationModel>) : RestaurantDetailUiState
    object Loading : RestaurantDetailUiState
    data class Error(val throwable: Throwable) : RestaurantDetailUiState
}