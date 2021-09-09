package com.example.weather.di

import com.example.weather.data.local.LocalSource
import com.example.weather.data.local.LocalSourceImpl
import com.example.weather.data.remote.RemoteSource
import com.example.weather.data.remote.RemoteSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class DataSourceModule {
    @Binds
    abstract fun bindRemoteSource(remoteSource: RemoteSourceImpl): RemoteSource

    @Binds
    abstract fun bindLocalSource(localeSource: LocalSourceImpl): LocalSource
}