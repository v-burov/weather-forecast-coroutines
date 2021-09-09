package com.example.weather.data.local

import com.example.weather.data.api.ForecastResponse
import com.example.weather.domain.ForecastDetailsEntity
import com.example.weather.domain.ForecastEntity
import java.lang.IllegalStateException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class LocalSourceImpl @Inject constructor() : LocalSource {

    private val mapForecasts = HashMap<Long, ForecastDetailsEntity>()

    override suspend fun saveForecast(forecastResponse: ForecastResponse) {
        forecastResponse.list.forEach { forecastItem ->
            val weather = forecastItem.weather.first() //first weather is primary by default
            mapForecasts[forecastItem.dt] = ForecastDetailsEntity(
                date = forecastItem.dt,
                temperature = forecastItem.main.temp,
                weatherPerception = forecastItem.main.feelsLike,
                weatherCondition = weather.main,
                weatherDescription = weather.description
            )
        }
    }

    override suspend fun getForecastList(): List<ForecastEntity> {
        return mapForecasts.values.map {
            ForecastEntity(
                date = it.date,
                weatherCondition = it.weatherCondition,
                temperature = it.temperature
            )
        }
    }

    override suspend fun getForecastDetails(date: Long): ForecastDetailsEntity =
        mapForecasts[date] ?: throw IllegalStateException("There no forecast")

    override suspend fun clear() {
        mapForecasts.clear()
    }
}