package github.dev_playground.jeju_road.domain.usecase

import github.dev_playground.jeju_road.data.model.Restaurants
import github.dev_playground.jeju_road.data.repository.RestaurantRepository
import kotlinx.coroutines.CoroutineDispatcher

class GetRestaurantListUseCase(
    private val restaurantRepository: RestaurantRepository,
    ioDispatcher: CoroutineDispatcher
) : CoroutineUseCase<Int, Restaurants>(ioDispatcher) {

    override suspend fun execute(param: Int): Restaurants {
        return restaurantRepository.getRestaurantList(param)
    }

}