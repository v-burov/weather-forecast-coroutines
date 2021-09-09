package com.example.weather.domain

import com.example.weather.base.UseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ForecastSearchUseCase @Inject constructor(private val forecastRepository: ForecastRepository) :
    UseCase.UseCaseWithParams<ForecastSearchResult, ForecastSearchParams> {

    override suspend fun execute(params: ForecastSearchParams): ForecastSearchResult {
        return forecastRepository.loadForecast(params.cityName)
    }
}