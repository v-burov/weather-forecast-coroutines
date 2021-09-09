package com.example.weather.data.api

import retrofit2.http.GET
import retrofit2.http.Query

internal interface ApiService {

    @GET("data/2.5/forecast")
    suspend fun getForecast(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String
    ): ForecastResponse
}