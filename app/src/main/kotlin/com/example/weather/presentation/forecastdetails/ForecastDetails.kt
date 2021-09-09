package com.example.weather.presentation.forecastdetails

internal data class ForecastDetails(
    val temperature: Int,
    val weatherPerception: String,
    val weatherCondition: String,
    val weatherDescription: String
)