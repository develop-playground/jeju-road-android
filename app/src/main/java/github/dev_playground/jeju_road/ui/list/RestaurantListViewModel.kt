package github.dev_playground.jeju_road.ui.list

import android.icu.text.IDNA
import androidx.lifecycle.*
import github.dev_playground.jeju_road.data.model.Information
import github.dev_playground.jeju_road.data.model.Restaurants
import github.dev_playground.jeju_road.domain.usecase.GetRestaurantListUseCase
import github.dev_playground.jeju_road.domain.usecase.GetRestaurantPagingUseCase
import github.dev_playground.jeju_road.util.Event
import github.dev_playground.jeju_road.util.UiState
import github.dev_playground.jeju_road.util.toUiState
import kotlinx.coroutines.launch

class RestaurantListViewModel(
    private val getRestaurantListUseCase: GetRestaurantListUseCase,
    private val getRestaurantPagingUseCase: GetRestaurantPagingUseCase,
    private val handle: SavedStateHandle,
) : ViewModel() {
    private val SAVED_STATED_KEY = "savedStatedList"
    private val COUNT_KEY = "countKey"

    private val _restaurantList: MutableLiveData<UiState<Restaurants>> = MutableLiveData<UiState<Restaurants>>(UiState.loading())
    val restaurantList: LiveData<UiState<Restaurants>> = _restaurantList

    private val _onRestaurantClickEvent =  MutableLiveData<Event<Information>>()
    val onRestaurantClickEvent: LiveData<Event<Information>> = _onRestaurantClickEvent

    private val _bringRestaurantList: MutableLiveData<UiState<Restaurants>> = MutableLiveData<UiState<Restaurants>>()
    val bringRestaurantList: LiveData<UiState<Restaurants>> = _bringRestaurantList

    private var counter = handle.get<Int>(COUNT_KEY) ?: 0
        set(value) {
            handle[COUNT_KEY] = value
            field = value
        }

    val counterLiveData: LiveData<Int> = handle.getLiveData(COUNT_KEY)

    private var savedStateList = handle.get<MutableList<Information>>(SAVED_STATED_KEY)
        set(value) {
            handle.set(SAVED_STATED_KEY, value)
            field = value
        }

    private val _currentRestaurantList: MutableLiveData<MutableList<Information>> = handle.getLiveData(SAVED_STATED_KEY)
    val currentRestaurantList: LiveData<MutableList<Information>> = _currentRestaurantList

    init {
        fetchRestaurantList()
    }

    fun fetchRestaurantList() = viewModelScope.launch {
        _restaurantList.value = getRestaurantListUseCase.invoke().toUiState()
    }

    fun callOnRestaurantClickEvent(data: Information) {
        _onRestaurantClickEvent.value = Event(data)
    }

    fun pagingRestaurantList() = viewModelScope.launch {
        _bringRestaurantList.value = getRestaurantPagingUseCase.invoke().toUiState()
    }

    fun managementRestaurantList(list: MutableList<Information>) = viewModelScope.launch {
        savedStateList = list
    }

    fun configurationTest() {
        ++counter
    }
}

