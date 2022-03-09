package github.dev_playground.jeju_road.domain.repository

import github.dev_playground.jeju_road.domain.model.Content
import github.dev_playground.jeju_road.domain.model.DetailInformation

interface RestaurantRepository {

    suspend fun getRestaurantList(param: Int): List<Content>

    suspend fun getRestaurantDetail(id: Long): DetailInformation

}