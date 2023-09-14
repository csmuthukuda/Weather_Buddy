package com.csmprojects.core.data.remote

import com.csmprojects.core.data.model.WeatherDataResource
import com.csmprojects.core.data.util.constants.WEATHER_DATA_BASE_URL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface WeatherDataApi {
    @GET
    suspend fun getWeatherData(
        @Url url : String,
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ):Response<WeatherDataResource>
}