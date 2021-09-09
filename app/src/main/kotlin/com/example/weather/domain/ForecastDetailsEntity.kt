package com.example.weather.domain

internal data class ForecastDetailsEntity(
    val date: Long,
    val temperature: Double,
    val weatherPerception: Double,
    val weatherCondition: String,
    val weatherDescription: String
)