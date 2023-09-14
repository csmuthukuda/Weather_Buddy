package com.csmprojects.core.data.di

import com.csmprojects.core.data.remote.WeatherDataApi
import com.csmprojects.core.data.repository.DefaultWeatherDataRepository
import com.csmprojects.core.data.repository.WeatherDataRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object WeatherDataModule {
    @Provides
    @Singleton
    fun provideWeatherDataApi(): WeatherDataApi {
        return Retrofit.Builder()
            .baseUrl("http://test.com")
            .addConverterFactory(
                Json {
                    ignoreUnknownKeys = true
                }.asConverterFactory("application/json".toMediaType()),
            )
            .build()
            .create(WeatherDataApi::class.java)
    }

    @Provides
    @Singleton
    fun bindsWeatherDataRepository(
        api: WeatherDataApi
    ): WeatherDataRepository {
        return DefaultWeatherDataRepository(api)
    }
}