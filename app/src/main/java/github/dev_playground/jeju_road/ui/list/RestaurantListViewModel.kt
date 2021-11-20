package github.dev_playground.jeju_road.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import github.dev_playground.jeju_road.domain.model.Information
import github.dev_playground.jeju_road.domain.model.Restaurants
import github.dev_playground.jeju_road.domain.usecase.GetRestaurantListUseCase
import github.dev_playground.jeju_road.util.Event
import github.dev_playground.jeju_road.util.UiState
import github.dev_playground.jeju_road.util.toUiState
import kotlinx.coroutines.launch

class RestaurantListViewModel(
    private val getRestaurantListUseCase: GetRestaurantListUseCase
) : ViewModel() {

    private val _restaurantList: MutableLiveData<UiState<Restaurants>> = MutableLiveData<UiState<Restaurants>>(UiState.loading())
    val restaurantList: LiveData<UiState<Restaurants>> = _restaurantList

    private val _onRestaurantClickEvent =  MutableLiveData<Event<Information>>()
    val onRestaurantClickEvent: LiveData<Event<Information>> = _onRestaurantClickEvent

    init {
        fetchRestaurantList()
    }

    fun fetchRestaurantList() = viewModelScope.launch {
        _restaurantList.value = getRestaurantListUseCase.invoke().toUiState()
    }

    fun callOnRestaurantClickEvent(data: Information) {
        _onRestaurantClickEvent.value = Event(data)
    }

}