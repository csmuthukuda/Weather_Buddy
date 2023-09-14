package com.csmprojects.core.domain

import com.csmprojects.core.data.model.WeatherDataResource
import com.csmprojects.core.data.repository.WeatherDataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWeatherDataUseCase@Inject constructor(
    private val weatherDataRepository: WeatherDataRepository
) {
      suspend operator fun invoke(latitude:Double, longitude:Double): WeatherDataResource? {
        return weatherDataRepository.getWeatherData(latitude, longitude)
    }
}