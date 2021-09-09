package com.example.weather.domain

import com.example.weather.base.UseCase
import com.example.weather.presentation.forecastdetails.ForecasrDetailsParams
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ForecastDetailsUseCase @Inject constructor(private val forecastRepository: ForecastRepository) :
    UseCase.UseCaseWithParams<ForecastDetailsEntity, ForecasrDetailsParams> {

    override suspend fun execute(params: ForecasrDetailsParams): ForecastDetailsEntity =
        forecastRepository.loadForecastDetails(params.date)
}