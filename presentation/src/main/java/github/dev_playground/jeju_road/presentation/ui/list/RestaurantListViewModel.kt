package github.dev_playground.jeju_road.presentation.ui.list

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import github.dev_playground.jeju_road.domain.model.Content
import github.dev_playground.jeju_road.domain.usecase.GetRestaurantListUseCase
import github.dev_playground.jeju_road.presentation.util.*
import kotlinx.coroutines.launch

class RestaurantListViewModel(
    private val getRestaurantListUseCase: GetRestaurantListUseCase
) : ViewModel() {

    private val pager = Pager()

    private val _contentListState: MutableLiveData<UiState<List<Content>>> = MutableLiveData(UiState.loading())
    val contentListState: LiveData<UiState<List<Content>>> = _contentListState

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

    fun refreshContentList() {
        saveState(null)
        pager.reset()

        _contentListState.value = UiState.loading()
        viewModelScope.launch {
            loadContentList(false)
        }
    }

    private suspend fun loadContentList(isFetch: Boolean = true) {
        pager.load { param ->
            val result = getRestaurantListUseCase.invoke(param).toUiState()

            if (isFetch) {
                val oldList = _contentListState.value?.data ?: emptyList()
                val newList = result.data ?: emptyList()

                _contentListState.value = result.copy(data = oldList + newList)
            } else {
                _contentListState.value = result
            }

            result.data != null && result.data.isNotEmpty()
        }
    }

    fun saveState(state: Parcelable?) {
        _recyclerState.value = state
    }

}

