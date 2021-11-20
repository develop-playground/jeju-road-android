package github.dev_playground.jeju_road.domain.usecase

import github.dev_playground.jeju_road.data.model.Restaurants
import github.dev_playground.jeju_road.data.repository.RestaurantRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay

class GetRestaurantListUseCase(
    private val restaurantRepository: RestaurantRepository,
    ioDispatcher: CoroutineDispatcher
) : NonParamCoroutineUseCase<Restaurants>(ioDispatcher) {

    override suspend fun execute(): Restaurants {
        return restaurantRepository.getRestaurantList()
    }

}