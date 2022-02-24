package github.dev_playground.jeju_road.domain.repository

import github.dev_playground.jeju_road.domain.model.Information

interface RestaurantRepository {

    suspend fun getRestaurantList(): List<Information>

}