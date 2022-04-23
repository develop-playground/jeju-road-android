package github.dev_playground.jeju_road.presentation.ui.list

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import github.dev_playground.jeju_road.domain.model.Content
import github.dev_playground.jeju_road.domain.usecase.GetRestaurantListUseCase
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

    private val _recyclerState: MutableLiveData<Parcelable?> = MutableLiveData(null)
    val recyclerState: LiveData<Parcelable?> get() = _recyclerState

    init {
        refreshContentList()
    }

    fun fetchContentList() {
        viewModelScope.launch {
            loadContentList()
        }
    }

    fun addContentList(contentList: List<Content>) {
        _contentList.value = (_contentList.value ?: emptyList()) + contentList
    }

    fun refreshContentList() {
        _contentList.value = emptyList()
        saveState(null)
        pager.reset()

        viewModelScope.launch {
            _contentListState.value = UiState.loading()
            loadContentList()
        }
    }

    private suspend fun loadContentList() {
        pager.load { param ->
            val result = getRestaurantListUseCase.invoke(param).toUiState()
            _contentListState.value = result

            result.data != null && result.data.isNotEmpty()
        }
    }

    fun saveState(state: Parcelable?) {
        _recyclerState.value = state
    }

}

