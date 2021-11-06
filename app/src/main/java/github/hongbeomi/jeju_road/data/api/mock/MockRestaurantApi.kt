package github.hongbeomi.jeju_road.data.api.mock

import android.content.Context
import com.google.gson.Gson
import github.hongbeomi.jeju_road.data.api.RestaurantApi
import github.hongbeomi.jeju_road.data.model.RestaurantData
import github.hongbeomi.jeju_road.data.model.RestaurantDetailData
import github.hongbeomi.jeju_road.util.loadAsset
import retrofit2.http.GET

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