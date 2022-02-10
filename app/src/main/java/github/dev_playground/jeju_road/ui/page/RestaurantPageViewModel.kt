package github.dev_playground.jeju_road.ui.page

import androidx.lifecycle.*
import github.dev_playground.jeju_road.data.model.RestaurantDetail
import github.dev_playground.jeju_road.domain.usecase.GetRestaurantDetailUseCase
import github.dev_playground.jeju_road.util.UiState
import github.dev_playground.jeju_road.util.toUiState

class RestaurantPageViewModel(
    private val getRestaurantPageUseCase: GetRestaurantDetailUseCase
) : ViewModel() {

    val id: MutableLiveData<Long> = MutableLiveData<Long>()

    val restaurantDetail: LiveData<UiState<RestaurantDetail>> = id.switchMap {
        liveData {
            emit(UiState.loading())
            emit(getRestaurantPageUseCase.invoke(it).toUiState())
        }
    }

}