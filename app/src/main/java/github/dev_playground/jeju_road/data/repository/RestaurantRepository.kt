package github.dev_playground.jeju_road.data.repository

import github.dev_playground.jeju_road.data.model.Restaurants
import github.dev_playground.jeju_road.data.model.RestaurantDetail

interface RestaurantRepository {

    suspend fun getRestaurantList(): Restaurants

    suspend fun getRestaurantDetail(id: Long): RestaurantDetail

    suspend fun getRestaurantPaging(): Restaurants
}