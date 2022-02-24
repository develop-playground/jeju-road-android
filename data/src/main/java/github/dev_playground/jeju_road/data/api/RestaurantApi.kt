package github.dev_playground.jeju_road.data.api

import github.dev_playground.jeju_road.data.model.RestaurantData
import github.dev_playground.jeju_road.data.model.RestaurantDetailData
import retrofit2.http.GET
import retrofit2.http.Path

interface RestaurantApi {

    @GET("/restaurant")
    suspend fun getRestaurantList(): RestaurantData

    @GET("/restaurant/{id}")
    suspend fun getRestaurantDetail(@Path("id") id: String): RestaurantDetailData

}