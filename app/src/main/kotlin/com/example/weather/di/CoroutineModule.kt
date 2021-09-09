package com.example.weather.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

@InstallIn(SingletonComponent::class)
@Module
internal class CoroutineModule {

    @Provides
    @DispatcherDefault
    fun provideComputationDispatcher(): CoroutineDispatcher = Dispatchers.Default

}