package com.csmprojects.feature.summary

import com.csmprojects.core.common.LayoutStructure
import com.csmprojects.core.data.model.WeatherDataResource

sealed interface SummaryUiState {
    /**
     * The data is still loading.
     */
    object Loading : SummaryUiState

    /**
     * The data is loaded successfully
     */
    data class Success(

        val weatherData: WeatherDataResource,
        val uiLayout: LayoutStructure
    ) : SummaryUiState

    /**
     * The data is null
     */
    object Fail: SummaryUiState
}

