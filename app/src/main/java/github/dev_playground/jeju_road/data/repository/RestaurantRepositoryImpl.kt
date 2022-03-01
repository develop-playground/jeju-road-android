package github.dev_playground.jeju_road.data.repository

import github.dev_playground.jeju_road.data.api.RestaurantApi
import github.dev_playground.jeju_road.data.model.RestaurantDetail
import github.dev_playground.jeju_road.data.model.Restaurants

class RestaurantRepositoryImpl(
    private val restaurantApi: RestaurantApi
): RestaurantRepository {

    private var id = 1L

    override suspend fun getRestaurantList(page: Int): Restaurants {
        return runCatching {
            val list = restaurantApi.getRestaurantList(page)
            list.copy(
                informationList = list.informationList.map { info ->
                    id++
                    info.copy(id = id)
                }
            )
        }.getOrThrow()
    }

    override suspend fun getRestaurantDetail(id: Long): RestaurantDetail {
        return runCatching {
            restaurantApi.getRestaurantDetail(id)
        }.getOrThrow()
    }

}