package github.dev_playground.jeju_road.presentation.ui.loading

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import github.dev_playground.jeju_road.presentation.util.UiState
import github.dev_playground.jeju_road.presentation.util.onSuccess

class LoadingEventViewModel : ViewModel() {

    private val _loadingState: MutableLiveData<UiState<*>> = MutableLiveData<UiState<*>>()
    val loadingState: LiveData<UiState<*>> = _loadingState

    fun <T> setLoadingState(state: UiState<T>, success: (T) -> Unit) {
        _loadingState.value = state
        state.onSuccess(success)
    }

}