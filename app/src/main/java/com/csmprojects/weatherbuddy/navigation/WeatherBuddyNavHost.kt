package com.csmprojects.weatherbuddy.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.csmprojects.feature.summary.navigation.summaryScreen


@Composable
fun WeatherBuddyNavHost(
    navController: NavHostController,
    startDestination: String = "summary_route"
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {

        summaryScreen()

    }
}