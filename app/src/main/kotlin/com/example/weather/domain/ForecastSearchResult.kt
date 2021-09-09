package com.example.weather.domain

internal sealed class ForecastSearchResult {
    data class Success(val forecastList: List<ForecastEntity>) : ForecastSearchResult()
    data class Error(val message: String) : ForecastSearchResult()
}