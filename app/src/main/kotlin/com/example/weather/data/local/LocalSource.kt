package com.example.weather.data.local

import com.example.weather.data.api.ForecastResponse
import com.example.weather.domain.ForecastDetailsEntity
import com.example.weather.domain.ForecastEntity

internal interface LocalSource {

    suspend fun saveForecast(forecastResponse: ForecastResponse)

    suspend fun getForecastList(): List<ForecastEntity>

    suspend fun getForecastDetails(date: Long): ForecastDetailsEntity

    suspend fun clear()
}