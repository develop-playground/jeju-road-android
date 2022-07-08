package github.dev_playground.jeju_road.data

import github.dev_playground.jeju_road.domain.model.Content
import github.dev_playground.jeju_road.domain.model.DetailInformation
import github.dev_playground.jeju_road.domain.repository.RestaurantRepository


class FakeRestaurantRepositoryImpl : RestaurantRepository {
    override suspend fun getRestaurantList(param: Int): List<Content> {
        throw RuntimeException()
        return listOf(
            Content(
                id = 1,
                "맛집",
                listOf("category"),
                "address",
                "image",
                "intro"
            )
        )
    }

    override suspend fun getRestaurantDetail(id: Long): DetailInformation {
        throw RuntimeException()
    }
}