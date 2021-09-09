package com.example.weather.data.remote

import com.example.weather.data.api.ForecastResponse
import com.example.weather.data.api.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class RemoteSourceImpl @Inject constructor(private val apiService: ApiService) : RemoteSource {

    companion object {
        private const val API_KEY = "65d00499677e59496ca2f318eb68c049"
    }

    override suspend fun getForecast(cityName: String): RemoteSourceResponse<ForecastResponse> {
        return RemoteSourceResponse.on { apiService.getForecast(cityName, API_KEY) }
    }
}