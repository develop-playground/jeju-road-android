package github.dev_playground.jeju_road.data

import github.dev_playground.jeju_road.domain.model.Content
import github.dev_playground.jeju_road.domain.model.DetailInformation
import github.dev_playground.jeju_road.domain.repository.RestaurantRepository


class FakeRestaurantRepositoryImpl : RestaurantRepository {
    var isExceptionCheck: Boolean = true

    override suspend fun getRestaurantList(param: Int): List<Content> {
        if (!isExceptionCheck) {
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
            isExceptionCheck = !isExceptionCheck
            throw RuntimeException()
        }
    }

    override suspend fun getRestaurantDetail(id: Long): DetailInformation {
        throw RuntimeException()
    }
}