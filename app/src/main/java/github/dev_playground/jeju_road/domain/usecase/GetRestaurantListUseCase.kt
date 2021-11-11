package github.dev_playground.jeju_road.domain.usecase

import github.dev_playground.jeju_road.data.repository.RestaurantRepository
import github.dev_playground.jeju_road.domain.model.Information
import github.dev_playground.jeju_road.domain.model.Restaurants
import kotlinx.coroutines.CoroutineDispatcher

class GetRestaurantListUseCase(
    private val restaurantRepository: RestaurantRepository,
    ioDispatcher: CoroutineDispatcher
): NonParamCoroutineUseCase<Restaurants>(ioDispatcher) {

    override suspend fun execute(): Restaurants {
        return Restaurants(
            restaurantRepository.getRestaurantList().informationList.map {
                Information(
                    id = it.id,
                    name = it.name,
                    category = it.category,
                    address = it.address,
                    image = it.image,
                    introduction = it.introduction
                )
            }
        )
    }

}