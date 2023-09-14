package com.csmprojects.core.data.repository.fake

import com.csmprojects.core.data.model.CurrentWeather
import com.csmprojects.core.data.model.DailyData
import com.csmprojects.core.data.model.HourlyData
import com.csmprojects.core.data.model.WeatherDataResource
import com.csmprojects.core.data.repository.WeatherDataRepository

class FakeWeatherDataRepository : WeatherDataRepository {
    private var isWeatherDataNull = false
    override suspend fun getWeatherData(latitude: Double, longitude: Double): WeatherDataResource? {
        return if (isWeatherDataNull) {
            null
        } else {
            //returning fake data for testing
            getWeatherData()
        }

    }

    fun setWeatherDataNull(value: Boolean) {
        isWeatherDataNull = value
    }

    fun getWeatherData(): WeatherDataResource {
        return WeatherDataResource(
            timezone = "TestTimeone",
            elevation = 15.5,
            latitude = 55.256,
            longitude = 11.589,
            current_weather = CurrentWeather(
                temperature = 25.4,
                windspeed = 12.4,
                winddirection = 152,
                weathercode = 4,
                time = "testtime"
            ),
            hourly = HourlyData(
                time = listOf("abc", "cde", "efg", "ghi"),
                weathercode = listOf(1, 2, 3, 4),
                precipitation_probability = listOf(1, 2, 3, 4),
                temperature_2m = listOf(1.5, 2.4, 5.8, 6.2)
            ),
            daily = DailyData(
                time = listOf("abc", "cde", "efg", "ghi"),
                weathercode = listOf(1, 2, 3, 4),
                precipitation_probability_max = listOf(1, 2, 3, 4),
                temperature_2m_max = listOf(1.5, 2.4, 5.8, 6.2)
            )
        )
    }
}