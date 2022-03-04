package github.dev_playground.jeju_road.data.api

import github.dev_playground.jeju_road.data.model.RestaurantDetail
import github.dev_playground.jeju_road.data.model.Restaurants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestaurantApi {
    @GET("restaurants")
    suspend fun getRestaurantList(@Query("page") param: Int): Restaurants

    @GET("restaurants/{id}")
    suspend fun getRestaurantDetail(@Path("id") id: Long): RestaurantDetail
}