package github.dev_playground.jeju_road.util


data class UiState<T>(
    val loading: Boolean = false,
    val exception: Throwable? = null,
    val data: T? = null
) {
    val hasError: Boolean
        get() = exception != null

    val initialLoad: Boolean
        get() = data == null && loading && !hasError

    @Suppress("UNCHECKED_CAST")
    fun <T> getOrThrow(): T {
        throwOnFailure()
        return data as T
    }

    private fun throwOnFailure() {
        if (hasError) throw exception!!
    }

    companion object {
        fun <T> loading(): UiState<T> = UiState(loading = true)

        fun <T> success(value: T): UiState<T> = UiState(data = value)

        fun <T> failure(exception: Throwable): UiState<T> = UiState(exception = exception)
    }
}

fun <T> Result<T>.toUiState(): UiState<T> {
    return when(this) {
        is Result.Success<T> -> UiState.success(this.data)
        Result.Loading -> UiState.loading()
        is Result.Error -> UiState.failure(this.exception)
    }
}