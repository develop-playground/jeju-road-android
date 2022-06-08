package github.dev_playground.jeju_road.domain.usecase

import github.dev_playground.jeju_road.domain.model.Content
import github.dev_playground.jeju_road.domain.repository.RestaurantRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay

class GetRestaurantListUseCase(
    private val restaurantRepository: RestaurantRepository,
    ioDispatcher: CoroutineDispatcher
): CoroutineUseCase<Int, List<Content>>(ioDispatcher) {

    override suspend fun execute(param: Int): List<Content> {
        return restaurantRepository.getRestaurantList(param)
    }

}