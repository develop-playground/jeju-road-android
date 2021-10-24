package github.hongbeomi.jeju_road.data.model

import com.google.gson.annotations.SerializedName

data class RestaurantData(
    val message: String,
    @SerializedName("information")
    val informationList: List<InformationData>
)
