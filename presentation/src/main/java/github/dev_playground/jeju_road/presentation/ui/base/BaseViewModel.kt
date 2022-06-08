package github.dev_playground.jeju_road.presentation.ui.base

import androidx.lifecycle.ViewModel
import github.dev_playground.jeju_road.presentation.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<S : UiState<S>>(
    initState: S
) : ViewModel() {

    private val _uiState = MutableStateFlow<S>(initState)
    val uiState = _uiState.asStateFlow()

    private val currentState: S
        get() = _uiState.value

    protected fun setLoadingState(action: S.() -> S) {
        val state = currentState
        //...
    }

    protected inline fun <T> UiState<T>.onSuccess(action: (T) -> Unit) = apply {
        data?.let(action)
    }

    protected inline fun <T> UiState<T>.onFailure(action: (Throwable) -> Unit) = apply {
        exception?.let(action)
    }

    protected inline fun <T> UiState<T>.onLoading(action: (Boolean) -> Unit) = apply {
        action.invoke(loading)
    }
}

