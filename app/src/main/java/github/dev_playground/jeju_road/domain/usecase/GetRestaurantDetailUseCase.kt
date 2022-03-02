package github.dev_playground.jeju_road.domain.usecase

import github.dev_playground.jeju_road.data.model.RestaurantDetail
import github.dev_playground.jeju_road.data.repository.RestaurantRepository
import kotlinx.coroutines.CoroutineDispatcher

class GetRestaurantDetailUseCase(
    private val restaurantRepository: RestaurantRepository,
    ioDispatcher: CoroutineDispatcher
): CoroutineUseCase<Long, RestaurantDetail>(ioDispatcher) {

    override suspend fun execute(param: Long): RestaurantDetail {
        return  restaurantRepository.getRestaurantDetail(param)
    }
}