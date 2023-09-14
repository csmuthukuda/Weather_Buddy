package com.csmprojects.feature.summary.navigation


import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.csmprojects.feature.summary.SummaryRoute

const val summaryNavigationRoute = "summary_route"

fun NavController.navigateToSummary(navOptions: NavOptions? = null) {
    this.navigate(summaryNavigationRoute, navOptions)
}


fun NavGraphBuilder.summaryScreen(

) {
    composable(
        route = summaryNavigationRoute,
    ) {
        SummaryRoute()
    }
}