package github.dev_playground.jeju_road.data.repository

import github.dev_playground.jeju_road.data.api.RestaurantApi
import github.dev_playground.jeju_road.data.model.Restaurants
import github.dev_playground.jeju_road.data.model.RestaurantDetail

class RestaurantRepositoryImpl(
    private val restaurantApi: RestaurantApi
): RestaurantRepository {

    override suspend fun getRestaurantList(): Restaurants {
        return runCatching {
            restaurantApi.getRestaurantList()
        }.getOrThrow()
    }

    override suspend fun getRestaurantDetail(id: Long): RestaurantDetail {
        return runCatching {
            restaurantApi.getRestaurantDetail(id)
        }.getOrThrow()
    }

}