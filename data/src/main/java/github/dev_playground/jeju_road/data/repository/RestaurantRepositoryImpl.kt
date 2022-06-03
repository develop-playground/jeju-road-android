package github.dev_playground.jeju_road.data.repository

import github.dev_playground.jeju_road.data.api.RestaurantApi
import github.dev_playground.jeju_road.data.model.toDomain
import github.dev_playground.jeju_road.domain.model.Content
import github.dev_playground.jeju_road.domain.model.DetailInformation
import github.dev_playground.jeju_road.domain.repository.RestaurantRepository

internal class RestaurantRepositoryImpl(
    private val restaurantApi: RestaurantApi
): RestaurantRepository {

    override suspend fun getRestaurantList(param: Int): List<Content> {
        return runCatching {
            restaurantApi.getRestaurantList(param).information.content.map { it.toDomain() }
        }.getOrThrow()
    }

    override suspend fun getRestaurantDetail(id: Long): DetailInformation {
        return runCatching {
            restaurantApi.getRestaurantDetail(id).detailInformation.toDomain()
        }.getOrThrow()
    }


}