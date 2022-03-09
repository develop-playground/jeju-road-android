package github.dev_playground.jeju_road.data.api

import github.dev_playground.jeju_road.data.model.RestaurantData
import github.dev_playground.jeju_road.data.model.RestaurantDetailData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestaurantApi {

    @GET("restaurants")
    suspend fun getRestaurantList(@Query("page") param: Int): RestaurantData

    @GET("restaurants/{id}")
    suspend fun getRestaurantDetail(@Path("id") param: Long): RestaurantDetailData

}