package github.dev_playground.jeju_road.presentation.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import github.dev_playground.jeju_road.domain.model.Content
import github.dev_playground.jeju_road.domain.usecase.GetRestaurantListUseCase
import github.dev_playground.jeju_road.presentation.util.Event
import github.dev_playground.jeju_road.presentation.util.Pager
import github.dev_playground.jeju_road.presentation.util.UiState
import github.dev_playground.jeju_road.presentation.util.toUiState
import kotlinx.coroutines.launch

class RestaurantListViewModel(
    private val getRestaurantListUseCase: GetRestaurantListUseCase
) : ViewModel() {

    private val pager = Pager()

    private val _contentListState: MutableLiveData<UiState<List<Content>>> = MutableLiveData(UiState.loading())
    val contentListState: LiveData<UiState<List<Content>>> = _contentListState

    private val _contentList: MutableLiveData<List<Content>> = MutableLiveData(emptyList())
    val contentList: LiveData<List<Content>> = _contentList

    private val _onRestaurantClickEvent =  MutableLiveData<Event<Content>>()
    val onRestaurantClickEvent: LiveData<Event<Content>> = _onRestaurantClickEvent

    init {
        fetchContentList()
    }

    fun fetchContentList() {
        viewModelScope.launch {
            pager.load { param ->
                val result = getRestaurantListUseCase.invoke(param).toUiState()
                _contentListState.value = result

                result.data != null && result.data.isNotEmpty()
            }
        }
    }

    fun addContentList(contentList: List<Content>) {
        _contentList.value = (_contentList.value ?: emptyList()) + contentList
    }

    fun refreshContentList() {
        _contentList.value = emptyList()
        pager.reset()
        fetchContentList()
    }

    fun callOnRestaurantClickEvent(data: Content) {
        _onRestaurantClickEvent.value = Event(data)
    }

}

