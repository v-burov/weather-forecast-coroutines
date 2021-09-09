package com.example.weather.data.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastResponse(
    val cod: String,
    val message: String,
    val list: List<ForecastItem>
)

@Serializable
data class ForecastItem(
    val dt: Long,
    val weather: List<Weather>,
    val main: Main
)

@Serializable
data class Main(
    val temp: Double,

    @SerialName("feels_like")
    val feelsLike: Double
)

@Serializable
data class Weather(val id: Int, val main: String, val description: String, val icon: String)