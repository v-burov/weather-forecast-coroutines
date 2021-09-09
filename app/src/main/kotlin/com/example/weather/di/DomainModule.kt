package com.example.weather.di

import com.example.weather.base.UseCase
import com.example.weather.domain.*
import com.example.weather.presentation.forecastdetails.ForecasrDetailsParams
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class DomainModule {

    @Binds
    abstract fun bindForecastSearchUseCase(forecastSearchUseCase: ForecastSearchUseCase): UseCase.UseCaseWithParams<ForecastSearchResult, ForecastSearchParams>

    @Binds
    abstract fun bindForecastDetailsUseCase(forecastDetailsUseCase: ForecastDetailsUseCase): UseCase.UseCaseWithParams<ForecastDetailsEntity, ForecasrDetailsParams>
}