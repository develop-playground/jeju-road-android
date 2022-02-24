package github.dev_playground.jeju_road.data.model

import com.google.gson.annotations.SerializedName
import github.dev_playground.jeju_road.domain.model.Information

data class RestaurantData(
    val message: String,
    @SerializedName("information")
    val informationList: List<InformationData>
)