package github.dev_playground.jeju_road.domain.usecase

import github.dev_playground.jeju_road.domain.model.Restaurants
import github.dev_playground.jeju_road.domain.repository.RestaurantRepository
import kotlinx.coroutines.CoroutineDispatcher

class GetRestaurantListUseCase(
    private val restaurantRepository: RestaurantRepository,
    ioDispatcher: CoroutineDispatcher
): NonParamCoroutineUseCase<Restaurants>(ioDispatcher) {

    override suspend fun execute(): Restaurants {
        return Restaurants(
            restaurantRepository.getRestaurantList()
        )
    }

}