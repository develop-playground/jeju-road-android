package github.dev_playground.jeju_road.data.model

import com.google.gson.annotations.SerializedName

internal data class RestaurantDetailData (
    val message: String,
    @SerializedName("information")
    val detailInformation: DetailInformationData
)