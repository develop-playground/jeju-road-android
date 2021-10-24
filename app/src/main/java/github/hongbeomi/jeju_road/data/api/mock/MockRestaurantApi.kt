package github.hongbeomi.jeju_road.data.api.mock

import github.hongbeomi.jeju_road.data.api.RestaurantApi
import github.hongbeomi.jeju_road.data.model.RestaurantData
import github.hongbeomi.jeju_road.data.model.RestaurantDetailData
import retrofit2.http.GET

interface MockRestaurantApi: RestaurantApi {

    @GET("/github/hongbeomi/jeju_load/main/app/src/main/assets/restaurant.json")
    override suspend fun getRestaurantList(): RestaurantData

    @GET("/github/hongbeomi/jeju_load/main/app/src/main/assets/restaurant_detail.json")
    override suspend fun getRestaurantDetail(id: String): RestaurantDetailData

}