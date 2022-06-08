package github.dev_playground.jeju_road.data.api.mock

import android.content.Context
import com.google.gson.Gson
import github.dev_playground.jeju_road.data.api.RestaurantApi
import github.dev_playground.jeju_road.data.model.InformationData
import github.dev_playground.jeju_road.data.model.RestaurantData
import github.dev_playground.jeju_road.data.model.RestaurantDetailData
import github.dev_playground.jeju_road.data.util.loadAsset

internal class MockRestaurantApi(
    private val context: Context
) : RestaurantApi {

    private val gson = Gson()

    override suspend fun getRestaurantList(param: Int): RestaurantData {
        val fileName = when (param) {
            0 -> "restaurant_page_1.json"
            1 -> "restaurant_page_2.json"
            2 -> "restaurant_page_3.json"
            else -> return RestaurantData(
                "empty data",
                InformationData(emptyList())
            )
        }
        return gson.fromJson(context.loadAsset(fileName), RestaurantData::class.java)
    }

    override suspend fun getRestaurantDetail(param: Long): RestaurantDetailData {
        return gson.fromJson(
            context.loadAsset("restaurant_detail.json"),
            RestaurantDetailData::class.java
        )
    }

}