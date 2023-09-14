package com.csmprojects.feature.summary

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.csmprojects.core.common.LayoutStructure
import com.csmprojects.core.data.model.DailyData
import com.csmprojects.core.data.model.HourlyData
import com.csmprojects.core.data.model.WeatherDataResource
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
internal fun SummaryRoute(
    modifier: Modifier = Modifier,
    viewModel: SummaryViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val isLoading = remember { mutableStateOf(false) }
    val summaryUiState by viewModel.summaryUiState.collectAsStateWithLifecycle()
    when (summaryUiState) {
        SummaryUiState.Fail -> {
            isLoading.value = false
            Toast.makeText(context,
                stringResource(R.string.failed_please_try_again), Toast.LENGTH_LONG).show()
        }

        SummaryUiState.Loading -> {
            isLoading.value = true
        }

        is SummaryUiState.Success -> {
            isLoading.value = false
            MaterialTheme {
                SummaryScreen(
                    weatherData = (summaryUiState as SummaryUiState.Success).weatherData,
                    layoutData = (summaryUiState as SummaryUiState.Success).uiLayout,
                    context = context
                )
            }

        }
    }

    AnimatedVisibility(
        visible = isLoading.value,
        modifier = Modifier
            .fillMaxSize()
            .zIndex(1f)
    ) {
        CircularProgIndicator(
            modifier = modifier
        )

    }

}

@Composable
internal fun SummaryScreen(
    modifier: Modifier = Modifier,
    weatherData: WeatherDataResource,
    layoutData: LayoutStructure,
    context: Context
) {

    Column(
        modifier
            .fillMaxSize()

    ) {
        Row(
            modifier
                .fillMaxWidth()
                .weight(1f, fill = true)
        ) {
            if (layoutData.top_left.visibility) {
                Column(
                    modifier
                        .weight(1f, fill = true)
                        .padding(all = 5.dp)
                        .align(Alignment.CenterVertically)

                ) {
                    GetWeatherData(
                        modifier = modifier,
                        component = layoutData.top_left.component,
                        weatherData = weatherData,
                        context = context
                    )
                }
            }
            if (layoutData.top_right.visibility) {
                Column(
                    modifier
                        .weight(1f, fill = true)
                        .padding(all = 5.dp)
                        .align(Alignment.CenterVertically)
                ) {
                    GetWeatherData(
                        modifier = modifier,
                        component = layoutData.top_right.component,
                        weatherData = weatherData,
                        context = context
                    )
                }
            }
        }
        Row(
            modifier
                .fillMaxWidth()
                .weight(1f, fill = true)
        ) {
            if (layoutData.middle_left.visibility) {
                Column(
                    modifier
                        .weight(1f, fill = true)
                        .padding(all = 5.dp)
                        .align(Alignment.CenterVertically)
                ) {
                    GetWeatherData(
                        modifier = modifier,
                        component = layoutData.middle_left.component,
                        weatherData = weatherData,
                        context = context
                    )
                }
            }
            if (layoutData.middle_right.visibility) {
                Column(
                    modifier
                        .weight(1f, fill = true)
                        .padding(horizontal = 10.dp)
                        .align(Alignment.CenterVertically)
                ) {
                    GetWeatherData(
                        modifier = modifier,
                        component = layoutData.middle_right.component,
                        weatherData = weatherData,
                        context = context
                    )
                }
            }
        }
        Row(
            modifier
                .fillMaxWidth()
                .weight(1f, fill = true)
        ) {
            if (layoutData.bottom_left.visibility) {
                Column(
                    modifier
                        .weight(1f, fill = true)
                        .padding(all = 5.dp)
                        .align(Alignment.CenterVertically)
                ) {
                    GetWeatherData(
                        modifier = modifier,
                        component = layoutData.bottom_left.component,
                        weatherData = weatherData,
                        context = context
                    )
                }
            }
            if (layoutData.bottom_right.visibility) {
                Column(
                    modifier
                        .weight(1f, fill = true)
                        .padding(all = 5.dp)
                        .align(Alignment.CenterVertically)
                ) {
                    GetWeatherData(
                        modifier = modifier,
                        component = layoutData.bottom_right.component,
                        weatherData = weatherData,
                        context = context
                    )
                }
            }
        }
    }
}


@Composable
private fun TemperatureScreen(
    modifier: Modifier,
    data: Double
) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp),
        text = stringResource(R.string.temperature),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.headlineLarge,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    )
    Text(
        modifier = modifier.fillMaxWidth(),
        text = "$data °C",
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.primary,
        style = MaterialTheme.typography.headlineLarge,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    )
}

@Composable
private fun WeatherCodeScreen(
    modifier: Modifier,
    data: Int,
    context: Context
) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp),
        text = stringResource(R.string.weather_interpretation),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.headlineLarge,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 20.sp
    )
    Text(
        modifier = modifier
            .fillMaxWidth(),
        text = getInfoFromWeatherCode(context ,data),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.primary,
        style = MaterialTheme.typography.headlineLarge,
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp
    )
}

@Composable
private fun WindSpeedScreen(
    modifier: Modifier,
    windSpeed: Double,
    windDirection: Int
) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp),
        text = stringResource(R.string.wind_speed),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.headlineLarge,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    )
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp),
        text = "$windSpeed Km/h",
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.primary,
        style = MaterialTheme.typography.headlineLarge,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    )
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp),
        text = stringResource(R.string.wind_direction),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.headlineLarge,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    )
    Text(
        modifier = modifier
            .fillMaxWidth(),
        text = "$windDirection °",
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.primary,
        style = MaterialTheme.typography.headlineLarge,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    )
}

@Composable
private fun GeneralInfoScreen(
    modifier: Modifier,
    elevation: Double,
    time: String,
    timeZone: String,
    latitude: Double,
    longitude: Double,

    ) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp),
        text = stringResource(R.string.general_information),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.headlineLarge,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 20.sp
    )
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {

        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
            text = stringResource(R.string.elevation),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp
        )
        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
            text = "$elevation m",
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        )
        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
            text = stringResource(R.string.latitude),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp
        )
        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
            text = "$latitude °",
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        )
        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
            text = stringResource(R.string.longitude),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp
        )
        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
            text = "$longitude °",
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        )
        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
            text = stringResource(R.string.date_and_time),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp
        )
        val parsedDate =
            LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME)
        val formattedDate = parsedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
            text = formattedDate,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        )

        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
            text = timeZone,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        )
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HourlyWeatherScreen(
    modifier: Modifier,
    data: HourlyData,
    context: Context
) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp),
        text = stringResource(R.string.hourly_forecast),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.headlineLarge,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    )
    LazyColumn {
        stickyHeader {
            Surface(modifier.fillParentMaxWidth()) {
                Row(
                    modifier
                        .fillMaxWidth()
                        .padding(all = 5.dp)
                        .background(MaterialTheme.colorScheme.background),

                    ) {
                    Text(
                        modifier = modifier
                            .padding(end = 5.dp)
                            .weight(1f),
                        text = stringResource(R.string.time),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 10.sp
                    )
                    Text(
                        modifier = modifier
                            .padding(end = 5.dp)
                            .weight(1f),
                        text = stringResource(R.string.forecast),
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 10.sp
                    )
                    Text(
                        modifier = modifier
                            .padding(end = 5.dp)
                            .weight(1f),
                        text = stringResource(R.string.temperature),
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 10.sp
                    )
                    Text(
                        modifier = modifier
                            .padding(end = 5.dp)
                            .weight(1f),
                        fontWeight = FontWeight.Bold,
                        text = stringResource(R.string.precipitation),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 10.sp
                    )
                }
            }


        }

        itemsIndexed(data.time) { index, item ->
            Row(
                modifier
                    .fillMaxWidth()
                    .padding(all = 5.dp),
            ) {
                val parsedDate =
                    LocalDateTime.parse(data.time[index], DateTimeFormatter.ISO_DATE_TIME)
                val formattedDate = parsedDate.format(DateTimeFormatter.ofPattern("HH:mm"))
                Text(
                    modifier = modifier
                        .padding(end = 5.dp)
                        .weight(1f),
                    text = formattedDate.toString(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 10.sp
                )
                Text(
                    modifier = modifier
                        .padding(end = 5.dp)
                        .weight(1f),
                    text = getInfoFromWeatherCode(context,data.weathercode[index]),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 10.sp
                )
                Text(
                    modifier = modifier
                        .padding(end = 5.dp)
                        .weight(1f),
                    text = "${data.temperature_2m[index]}°C",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 10.sp
                )
                Text(
                    modifier = modifier
                        .padding(end = 5.dp)
                        .weight(1f),
                    text = "${data.precipitation_probability[index]}%",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 10.sp
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun DailyWeatherScreen(
    modifier: Modifier,
    data: DailyData,
    context: Context
) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp),
        text = stringResource(R.string.daily_forecast),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.headlineLarge,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    )
    LazyColumn {
        stickyHeader {
            Surface(modifier.fillParentMaxWidth()) {
                Row(
                    modifier
                        .fillMaxWidth()
                        .padding(all = 5.dp),
                ) {
                    Text(
                        modifier = modifier
                            .padding(end = 5.dp)
                            .weight(1f),
                        text = stringResource(R.string.time),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 10.sp
                    )
                    Text(
                        modifier = modifier
                            .padding(end = 5.dp)
                            .weight(1f),
                        text = stringResource(R.string.forecast),
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 10.sp
                    )
                    Text(
                        modifier = modifier
                            .padding(end = 5.dp)
                            .weight(1f),
                        text = stringResource(R.string.temperature),
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 10.sp
                    )
                    Text(
                        modifier = modifier
                            .padding(end = 5.dp)
                            .weight(1f),
                        fontWeight = FontWeight.Bold,
                        text = stringResource(R.string.precipitation),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 10.sp
                    )
                }
            }

        }
        itemsIndexed(data.time) { index, item ->
            Row(
                modifier
                    .fillMaxWidth()
                    .padding(all = 5.dp)
            ) {
                Text(
                    modifier = modifier
                        .padding(end = 5.dp)
                        .weight(1f),
                    text = data.time[index],
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 10.sp
                )
                Text(
                    modifier = modifier
                        .padding(end = 5.dp)
                        .weight(1f),
                    text = getInfoFromWeatherCode(context,data.weathercode[index]),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 10.sp
                )
                Text(
                    modifier = modifier
                        .padding(end = 5.dp)
                        .weight(1f),
                    text = "${data.temperature_2m_max[index]}°C",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 10.sp
                )
                Text(
                    modifier = modifier
                        .padding(end = 5.dp)
                        .weight(1f),
                    text = "${data.precipitation_probability_max[index]}%",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 10.sp
                )
            }
        }
    }
}

@Composable
private fun GetWeatherData(
    modifier: Modifier,
    component: String,
    weatherData: WeatherDataResource,
    context: Context
) {
    return when (component) {
        "temperature" -> {
            TemperatureScreen(modifier = modifier, data = weatherData.current_weather.temperature)
        }

        "weathercode" -> {
            WeatherCodeScreen(modifier = modifier, data = weatherData.current_weather.weathercode, context = context)
        }

        "windspeed" -> {
            WindSpeedScreen(
                modifier = modifier,
                windSpeed = weatherData.current_weather.windspeed,
                windDirection = weatherData.current_weather.winddirection
            )
        }

        "general_info" -> {
            GeneralInfoScreen(
                modifier = modifier,
                latitude = weatherData.latitude,
                longitude = weatherData.longitude,
                elevation = weatherData.elevation,
                time = weatherData.current_weather.time,
                timeZone = weatherData.timezone,
            )
        }

        "hourly_weather_summary" -> {
            HourlyWeatherScreen(modifier = modifier, data = weatherData.hourly,context)
        }

        "daily_weather_summary" -> {
            DailyWeatherScreen(modifier = modifier, data = weatherData.daily,context)
        }

        else -> {

        }
    }
}

@Composable
fun CircularProgIndicator(
    modifier: Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .clickable(enabled = false) { },
        contentAlignment = Alignment.Center,

        ) {
        CircularProgressIndicator(
            modifier.fillMaxSize(0.12f),
        )
    }
}

private fun getInfoFromWeatherCode(context:Context, weatherCode: Int): String {
    return when (weatherCode) {
        0 -> context.getString(R.string.clear_sky)
        1, 2, 3 -> context.getString(R.string.mainly_clear_partly_cloudy_and_overcast)
        45, 48 -> context.getString(R.string.fog_and_depositing_rime_fog)
        51, 53, 55 -> context.getString(R.string.drizzle_light_moderate_and_dense_intensity)
        56, 57 -> context.getString(R.string.freezing_drizzle_light_and_dense_intensity)
        61, 63, 65 -> context.getString(R.string.rain_slight_moderate_and_heavy_intensity)
        66, 67 -> context.getString(R.string.freezing_rain_light_and_heavy_intensity)
        71, 73, 75 -> context.getString(R.string.snow_fall_slight_moderate_and_heavy_intensity)
        77 -> context.getString(R.string.snow_grains)
        80, 81, 82 -> context.getString(R.string.rain_showers_slight_moderate_and_violent)
        85, 86 -> context.getString(R.string.snow_showers_slight_and_heavy)
        95 -> context.getString(R.string.thunderstorm_slight_or_moderate)
        96, 99 -> context.getString(R.string.thunderstorm_with_slight_and_heavy_hail)
        else -> context.getString(R.string.data_not_found)
    }
}