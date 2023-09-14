package com.csmprojects.core.data.repository


import android.location.Location
import com.csmprojects.core.data.model.WeatherDataResource
import kotlinx.coroutines.flow.Flow

interface WeatherDataRepository {
    suspend fun getWeatherData(latitude:Double, longitude:Double): WeatherDataResource?
}