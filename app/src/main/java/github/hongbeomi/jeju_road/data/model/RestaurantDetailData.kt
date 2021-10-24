package github.hongbeomi.jeju_road.data.model

import com.google.gson.annotations.SerializedName

data class RestaurantDetailData (
    val message: String,
    @SerializedName("information")
    val detailInformationList: List<DetailInformationData>
)