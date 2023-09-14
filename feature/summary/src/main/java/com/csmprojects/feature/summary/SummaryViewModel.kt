package com.csmprojects.feature.summary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csmprojects.core.common.LayoutStructure
import com.csmprojects.core.data.model.WeatherDataResource
import com.csmprojects.core.domain.GetUiLayoutUseCase
import com.csmprojects.core.domain.GetWeatherDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SummaryViewModel @Inject constructor(
    private val getUiLayoutUseCase: GetUiLayoutUseCase,
    private val getWeatherDataUseCase: GetWeatherDataUseCase
) : ViewModel() {
    private val _summaryUiState = MutableStateFlow<SummaryUiState>(SummaryUiState.Loading)
    val summaryUiState = _summaryUiState.asStateFlow()

    init {
        viewModelScope.launch {
            _summaryUiState.value = SummaryUiState.Loading
            getWeatherDataUseCase(57.708870, 11.974560).also { weatherData ->
                if (weatherData != null) {
                    val layout = getUiLayoutUseCase()
                    _summaryUiState.value = SummaryUiState.Success(weatherData, layout)
                }else{
                    _summaryUiState.value = SummaryUiState.Fail
                }
            }
        }
    }

}