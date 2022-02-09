package github.dev_playground.jeju_road.data.api

import github.dev_playground.jeju_road.data.model.Restaurants
import github.dev_playground.jeju_road.data.model.RestaurantDetail
import retrofit2.http.GET
import retrofit2.http.Path

interface RestaurantApi {
    @GET("/restaurant")
    suspend fun getRestaurantList(): Restaurants

    @GET("/restaurant/{id}")
    suspend fun getRestaurantDetail(@Path("id") id: Long): RestaurantDetail


}