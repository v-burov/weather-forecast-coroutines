package com.example.weather.di

import com.example.weather.data.ForecastRepositoryImpl
import com.example.weather.domain.ForecastRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class RepositoryModule {
    @Binds
    abstract fun bindForecastRepository(forecastRepository: ForecastRepositoryImpl): ForecastRepository
}