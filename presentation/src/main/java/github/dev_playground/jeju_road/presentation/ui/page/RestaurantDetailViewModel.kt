package github.dev_playground.jeju_road.presentation.ui.page

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import github.dev_playground.jeju_road.domain.usecase.GetRestaurantDetailUseCase
import github.dev_playground.jeju_road.presentation.model.RestaurantDetailInformationModel
import github.dev_playground.jeju_road.presentation.model.RestaurantInformationModel
import github.dev_playground.jeju_road.presentation.model.RestaurantIntroductionModel

class RestaurantDetailViewModel(
    private val id: Long,
    private val getRestaurantPageUseCase: GetRestaurantDetailUseCase
) : ViewModel() {

    val detailInformationState: LiveData<RestaurantDetailUiState> =
        liveData {
            emit(RestaurantDetailUiState.Loading)

            val result = getRestaurantPageUseCase.invoke(id)
            result.onSuccess {
                emit(
                    RestaurantDetailUiState.Success(
                        listOf(
                            RestaurantIntroductionModel.toPresentation(it),
                            RestaurantDetailInformationModel.toPresentation(it)
                        )
                    )
                )
            }.onFailure {
                emit(RestaurantDetailUiState.Error(it))
            }
        }
}

sealed interface RestaurantDetailUiState {
    data class Success(val informationList: List<RestaurantInformationModel>) :
        RestaurantDetailUiState

    object Loading : RestaurantDetailUiState
    data class Error(val throwable: Throwable) : RestaurantDetailUiState
}