package github.dev_playground.jeju_road.data.api.mock

import android.content.Context
import com.google.gson.Gson
import github.dev_playground.jeju_road.data.api.RestaurantApi
import github.dev_playground.jeju_road.data.model.RestaurantData
import github.dev_playground.jeju_road.data.model.RestaurantDetailData
import github.dev_playground.jeju_road.data.util.loadAsset

class MockRestaurantApi(
    private val context: Context
): RestaurantApi {

    private val gson = Gson()

    override suspend fun getRestaurantList(): RestaurantData {
        return gson.fromJson(context.loadAsset("restaurant.json"), RestaurantData::class.java)
    }

    override suspend fun getRestaurantDetail(id: String): RestaurantDetailData {
        return gson.fromJson(context.loadAsset("restaurant_detail.json"), RestaurantDetailData::class.java)
    }

}