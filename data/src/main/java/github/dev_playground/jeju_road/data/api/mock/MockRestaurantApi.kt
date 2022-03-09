package github.dev_playground.jeju_road.data.api.mock

import android.content.Context
import com.google.gson.Gson
import github.dev_playground.jeju_road.data.api.RestaurantApi
import github.dev_playground.jeju_road.data.model.RestaurantData
import github.dev_playground.jeju_road.data.model.RestaurantDetailData
import github.dev_playground.jeju_road.data.util.loadAsset

class MockRestaurantApi(
    private val context: Context
) : RestaurantApi {

    private val gson = Gson()

    override suspend fun getRestaurantList(param: Int): RestaurantData {
        return gson.fromJson(context.loadAsset("restaurant.json"), RestaurantData::class.java)
    }

    override suspend fun getRestaurantDetail(param: Long): RestaurantDetailData {
        return gson.fromJson(
            context.loadAsset("restaurant_detail.json"),
            RestaurantDetailData::class.java
        )
    }

}