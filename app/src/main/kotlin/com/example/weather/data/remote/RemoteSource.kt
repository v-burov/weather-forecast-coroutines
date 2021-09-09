package com.example.weather.data.remote

import com.example.weather.data.api.ForecastResponse

internal interface RemoteSource {

    suspend fun getForecast(cityName: String): RemoteSourceResponse<ForecastResponse>
}