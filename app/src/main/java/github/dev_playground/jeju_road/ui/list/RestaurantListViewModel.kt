package github.dev_playground.jeju_road.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import github.dev_playground.jeju_road.data.model.Content
import github.dev_playground.jeju_road.data.model.Restaurant
import github.dev_playground.jeju_road.data.model.isNotNullOrEmpty
import github.dev_playground.jeju_road.domain.usecase.GetRestaurantListUseCase
import github.dev_playground.jeju_road.util.Event
import github.dev_playground.jeju_road.util.Pager
import github.dev_playground.jeju_road.util.UiState
import github.dev_playground.jeju_road.util.toUiState
import kotlinx.coroutines.launch

class RestaurantListViewModel(
    private val getRestaurantListUseCase: GetRestaurantListUseCase
) : ViewModel() {

    private val pager = Pager()

    private val _restaurantState: MutableLiveData<UiState<Restaurant>> = MutableLiveData()
    val restaurantState: LiveData<UiState<Restaurant>> = _restaurantState

    private val _contentList: MutableLiveData<List<Content>> = MutableLiveData(emptyList())
    val contentList: LiveData<List<Content>> = _contentList

    private val _onRestaurantClickEvent =  MutableLiveData<Event<Content>>()
    val onRestaurantClickEvent: LiveData<Event<Content>> = _onRestaurantClickEvent

    init {
        fetchRestaurant()
    }

    fun fetchRestaurant() {
        viewModelScope.launch {
            pager.load { param ->
                val result = getRestaurantListUseCase.invoke(param).toUiState()
                _restaurantState.value = result

                result.data?.isNotNullOrEmpty()
            }
        }
    }

    fun addContentList(contentList: List<Content>) {
        _contentList.value = (_contentList.value ?: emptyList()) + contentList
    }


    fun refreshContentList() {
        _contentList.value = emptyList()
        pager.reset()
        fetchRestaurant()
    }

    fun callOnRestaurantClickEvent(data: Content) {
        _onRestaurantClickEvent.value = Event(data)
    }

}

