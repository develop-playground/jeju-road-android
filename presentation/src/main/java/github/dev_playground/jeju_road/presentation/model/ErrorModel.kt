package github.dev_playground.jeju_road.presentation.model

data class ErrorModel(
    val timestamp: String,
    val status: Int,
    val error: String,
    val path: String,
)