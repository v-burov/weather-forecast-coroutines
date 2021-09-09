package com.example.weather.domain

internal interface ForecastRepository {

    suspend fun loadForecast(cityName: String): ForecastSearchResult

    suspend fun loadForecastDetails(date: Long): ForecastDetailsEntity
}