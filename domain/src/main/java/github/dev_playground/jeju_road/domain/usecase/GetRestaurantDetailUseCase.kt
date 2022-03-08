package github.dev_playground.jeju_road.domain.usecase

import github.dev_playground.jeju_road.domain.model.DetailInformation
import github.dev_playground.jeju_road.domain.repository.RestaurantRepository
import kotlinx.coroutines.CoroutineDispatcher

class GetRestaurantDetailUseCase(
    private val restaurantRepository: RestaurantRepository,
    ioDispatcher: CoroutineDispatcher
): CoroutineUseCase<Long, DetailInformation>(ioDispatcher) {

    override suspend fun execute(param: Long): DetailInformation {
        return  restaurantRepository.getRestaurantDetail(param)
    }
}