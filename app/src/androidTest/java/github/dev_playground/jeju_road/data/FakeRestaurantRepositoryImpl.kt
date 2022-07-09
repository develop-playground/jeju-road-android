package github.dev_playground.jeju_road.data

import github.dev_playground.jeju_road.domain.model.Content
import github.dev_playground.jeju_road.domain.model.DetailInformation
import github.dev_playground.jeju_road.domain.repository.RestaurantRepository


class FakeRestaurantRepositoryImpl : RestaurantRepository {
    var flag: Boolean = false

    override suspend fun getRestaurantList(param: Int): List<Content> {
        if (flag) {
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
        } else {
            throw RuntimeException()
        }
    }

    override suspend fun getRestaurantDetail(id: Long): DetailInformation {
        throw RuntimeException()
    }
}