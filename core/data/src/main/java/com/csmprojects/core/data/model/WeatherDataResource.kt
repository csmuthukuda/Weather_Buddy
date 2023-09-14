package com.csmprojects.core.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class WeatherDataResource(
    val timezone: String,
    val elevation: Double,
    val latitude: Double,
    val longitude: Double,
    val current_weather: CurrentWeather,
    val hourly: HourlyData,
    val daily: DailyData

)
@Serializable
@SerialName("current_weather")
data class CurrentWeather(
    val temperature: Double,
    val windspeed: Double,
    val winddirection: Int,
    val weathercode: Int,
    val time: String
)
@Serializable
@SerialName("hourly")
data class HourlyData(
    val time:List<String>,
    val temperature_2m:List<Double>,
    val weathercode:List<Int>,
    val precipitation_probability:List<Int>
)
@Serializable
@SerialName("daily")
data class DailyData(
    val time:List<String>,
    val temperature_2m_max:List<Double>,
    val weathercode:List<Int>,
    val precipitation_probability_max:List<Int>
)


