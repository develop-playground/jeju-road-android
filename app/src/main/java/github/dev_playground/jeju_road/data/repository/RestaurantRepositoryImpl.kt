package github.dev_playground.jeju_road.data.repository

import github.dev_playground.jeju_road.data.api.RestaurantApi
import github.dev_playground.jeju_road.data.model.RestaurantDetail
import github.dev_playground.jeju_road.data.model.Restaurants

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

    private var id = 1L

    override suspend fun getRestaurantPaging(): Restaurants {
        return runCatching {
            val list = restaurantApi.getRestaurantList()
            list.copy(
                informationList = list.informationList.map { info ->
                    id++
                    info.copy(id = id)
                }
            )
        }.getOrThrow()
    }
}