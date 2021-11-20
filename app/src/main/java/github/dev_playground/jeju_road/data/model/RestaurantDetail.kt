package github.dev_playground.jeju_road.data.model

import com.google.gson.annotations.SerializedName

data class RestaurantDetail (
    val message: String,
    @SerializedName("information")
    val detailInformationList: List<DetailInformation>
)