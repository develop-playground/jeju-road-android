package github.dev_playground.jeju_road.domain.repository

import github.dev_playground.jeju_road.domain.model.DetailInformation
import github.dev_playground.jeju_road.domain.model.Information

interface RestaurantRepository {

    suspend fun getRestaurantList(param: Int): List<Information>

    suspend fun getRestaurantDetail(id: Long): DetailInformation

}