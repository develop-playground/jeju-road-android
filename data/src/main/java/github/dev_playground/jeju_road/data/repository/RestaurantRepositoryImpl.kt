package github.dev_playground.jeju_road.data.repository

import github.dev_playground.jeju_road.data.api.RestaurantApi
import github.dev_playground.jeju_road.data.model.toDomain
import github.dev_playground.jeju_road.domain.model.Information
import github.dev_playground.jeju_road.domain.repository.RestaurantRepository

class RestaurantRepositoryImpl(
    private val restaurantApi: RestaurantApi
): RestaurantRepository {

    override suspend fun getRestaurantList(): List<Information> {
        return runCatching {
            restaurantApi.getRestaurantList().informationList.map { it.toDomain() }
        }.getOrThrow()
    }

}