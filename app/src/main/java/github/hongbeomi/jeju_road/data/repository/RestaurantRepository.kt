package github.hongbeomi.jeju_road.data.repository

import github.hongbeomi.jeju_road.data.model.RestaurantData
import github.hongbeomi.jeju_road.data.model.RestaurantDetailData

interface RestaurantRepository {

    suspend fun getRestaurantList(): RestaurantData

    suspend fun getRestaurantDetail(id: String): RestaurantDetailData

}