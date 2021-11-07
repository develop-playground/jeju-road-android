package github.hongbeomi.jeju_road.ui.loading

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import github.hongbeomi.jeju_road.util.UiState

class LoadingEventViewModel : ViewModel() {

    private val _loadingState: MutableLiveData<UiState<*>> = MutableLiveData<UiState<*>>()
    val loadingState: LiveData<UiState<*>> = _loadingState

    fun <T> setLoadingState(state: UiState<T>, success: (T) -> Unit) {
        _loadingState.value = state
        state.data?.let(success)
    }

}