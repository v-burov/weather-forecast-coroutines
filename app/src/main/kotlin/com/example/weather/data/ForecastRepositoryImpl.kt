package com.example.weather.data

import com.example.weather.data.api.ForecastResponse
import com.example.weather.data.local.LocalSource
import com.example.weather.data.remote.RemoteSource
import com.example.weather.data.remote.RemoteSourceResponse
import com.example.weather.di.DispatcherDefault
import com.example.weather.domain.ForecastRepository
import com.example.weather.domain.ForecastSearchResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ForecastRepositoryImpl @Inject constructor(
    private val remoteSource: RemoteSource,
    private val localSource: LocalSource,
    @DispatcherDefault private val defaultDispatcher: CoroutineDispatcher
) : ForecastRepository {

    override suspend fun loadForecast(cityName: String): ForecastSearchResult {
        localSource.clear()

        return when (val forecastResponse = remoteSource.getForecast(cityName)) {
            is RemoteSourceResponse.Success -> {
                saveForecast(forecastResponse.data)
                return ForecastSearchResult.Success(localSource.getForecastList())
            }
            is RemoteSourceResponse.Error -> {
                ForecastSearchResult.Error(forecastResponse.exception.message ?: "An error has occurred. Please try again later")
            }
        }
    }

    override suspend fun loadForecastDetails(date: Long) = localSource.getForecastDetails(date)

    private suspend fun saveForecast(forecastResponse: ForecastResponse) {
        withContext(defaultDispatcher) {
            localSource.saveForecast(forecastResponse)
        }
    }
}