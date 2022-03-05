package github.dev_playground.jeju_road.data.repository

import github.dev_playground.jeju_road.data.model.RestaurantDetail
import github.dev_playground.jeju_road.data.model.Restaurant

interface RestaurantRepository {

    suspend fun getRestaurantList(page: Int): Restaurant

    suspend fun getRestaurantDetail(id: Long): RestaurantDetail

}