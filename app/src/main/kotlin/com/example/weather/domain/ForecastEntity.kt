package com.example.weather.domain

internal data class ForecastEntity(val date: Long, val weatherCondition: String, val temperature: Double)