package github.dev_playground.jeju_road.domain.usecase

import github.dev_playground.jeju_road.data.model.Restaurant
import github.dev_playground.jeju_road.data.repository.RestaurantRepository
import kotlinx.coroutines.CoroutineDispatcher

class GetRestaurantListUseCase(
    private val restaurantRepository: RestaurantRepository,
    ioDispatcher: CoroutineDispatcher
) : CoroutineUseCase<Int, Restaurant>(ioDispatcher) {

    override suspend fun execute(param: Int): Restaurant {
        return restaurantRepository.getRestaurantList(param)
    }

}