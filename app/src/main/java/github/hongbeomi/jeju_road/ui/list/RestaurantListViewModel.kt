package github.hongbeomi.jeju_road.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import github.hongbeomi.jeju_road.domain.model.Restaurants
import github.hongbeomi.jeju_road.domain.usecase.GetRestaurantListUseCase
import github.hongbeomi.jeju_road.util.UiState
import github.hongbeomi.jeju_road.util.toUiState
import kotlinx.coroutines.launch

class RestaurantListViewModel(
    private val getRestaurantListUseCase: GetRestaurantListUseCase
) : ViewModel() {

    private val _restaurantList: MutableLiveData<UiState<Restaurants>> = MutableLiveData<UiState<Restaurants>>(UiState.loading())
    val restaurantList: LiveData<UiState<Restaurants>> = _restaurantList

    init {
        fetchRestaurantList()
    }

    fun fetchRestaurantList() = viewModelScope.launch {
        _restaurantList.value = getRestaurantListUseCase.invoke().toUiState()
    }

}