package github.dev_playground.jeju_road.data.repository

import github.dev_playground.jeju_road.data.api.RestaurantApi
import github.dev_playground.jeju_road.data.model.RestaurantData
import github.dev_playground.jeju_road.data.model.RestaurantDetailData

class RestaurantRepositoryImpl(
    private val restaurantApi: RestaurantApi
): RestaurantRepository {

    override suspend fun getRestaurantList(): RestaurantData {
        return runCatching {
            restaurantApi.getRestaurantList()
        }.getOrThrow()
    }

    override suspend fun getRestaurantDetail(id: String): RestaurantDetailData {
        return runCatching {
            restaurantApi.getRestaurantDetail(id)
        }.getOrThrow()
    }

}