package com.csmprojects.core.data.repository

import android.util.Log
import com.csmprojects.core.data.model.WeatherDataResource
import com.csmprojects.core.data.remote.WeatherDataApi
import com.csmprojects.core.data.util.constants.WEATHER_DATA_BASE_URL
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class DefaultWeatherDataRepository(
    private val api: WeatherDataApi
) : WeatherDataRepository {
    override suspend fun getWeatherData(latitude: Double, longitude: Double): WeatherDataResource? {
        return try {
            val data = api.getWeatherData(WEATHER_DATA_BASE_URL,latitude, longitude)
            if (data.isSuccessful) {
                data.body()
            } else {
                null
            }
        }catch (ex:Exception){
            Log.e("EXXX", ex.message.toString())
            null
        }



    }
}