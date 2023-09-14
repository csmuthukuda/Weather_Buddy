package com.csmprojects.feature.summary

import com.csmprojects.core.data.repository.fake.FakeUiLayoutRepository
import com.csmprojects.core.data.repository.fake.FakeWeatherDataRepository
import com.csmprojects.core.domain.GetUiLayoutUseCase
import com.csmprojects.core.domain.GetWeatherDataUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class SummaryViewModelTest {
    private lateinit var viewModel: SummaryViewModel
    private val weatherDataRepository = FakeWeatherDataRepository()
    private val uiLayoutRepository = FakeUiLayoutRepository()

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    @Before
    fun setup() {

    }

    @Test
    fun failed_weather_Data_call_sets_failed_status() = runTest {
        weatherDataRepository.setWeatherDataNull(true)
        viewModel = SummaryViewModel(
            getUiLayoutUseCase = GetUiLayoutUseCase(uiLayoutRepository),
            getWeatherDataUseCase = GetWeatherDataUseCase(weatherDataRepository)

        )
        val collectJob =
            launch(UnconfinedTestDispatcher()) { viewModel.summaryUiState.collect() }
        assertEquals(viewModel.summaryUiState.value, SummaryUiState.Fail)
        collectJob.cancel()

    }

    @Test
    fun successfull_weather_Data_call_sets_success_status() = runTest {
        weatherDataRepository.setWeatherDataNull(false)
        viewModel = SummaryViewModel(
            getUiLayoutUseCase = GetUiLayoutUseCase(uiLayoutRepository),
            getWeatherDataUseCase = GetWeatherDataUseCase(weatherDataRepository)

        )
        val collectJob =
            launch(UnconfinedTestDispatcher()) { viewModel.summaryUiState.collect() }
        assertEquals(
            viewModel.summaryUiState.value,
            SummaryUiState.Success(
                weatherDataRepository.getWeatherData(),
                uiLayoutRepository.getMockUiLayout()
            )
        )
        collectJob.cancel()

    }
}