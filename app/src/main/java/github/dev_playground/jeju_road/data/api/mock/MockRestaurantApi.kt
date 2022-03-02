package github.dev_playground.jeju_road.data.api.mock

import android.content.Context
import com.google.gson.Gson
import github.dev_playground.jeju_road.data.api.RestaurantApi
import github.dev_playground.jeju_road.data.model.RestaurantDetail
import github.dev_playground.jeju_road.data.model.Restaurants
import github.dev_playground.jeju_road.util.loadAsset

class MockRestaurantApi(
    private val context: Context
): RestaurantApi {

    private val gson = Gson()

    override suspend fun getRestaurantList(param: Int): Restaurants {
        return gson.fromJson(context.loadAsset("restaurant.json"), Restaurants::class.java)
    }

    override suspend fun getRestaurantDetail(id: Long): RestaurantDetail {
        return gson.fromJson(context.loadAsset("restaurant_detail.json"), RestaurantDetail::class.java)
    }

}