package github.dev_playground.jeju_road.data.model

import com.google.gson.annotations.SerializedName

data class Information(
    @SerializedName("content")
    val contentList: List<Content>
)
