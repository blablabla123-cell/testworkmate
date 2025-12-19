package com.example.testworkmate.common

import CountriesView
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testworkmate.details.presentation.CountryDetailsView

@Composable()
fun MainNavigation() {

    val navController: NavHostController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = CoreConstants.Destinations.Main.destination,
    ) {
        composable(CoreConstants.Destinations.Main.destination) {
            CountriesView(navController)
        }
        composable(CoreConstants.Destinations.Details.destination + "/{countryName}") {
            val countryName = it.arguments?.getString("countryName")
            if (countryName != null) {
                CountryDetailsView(navController, countryName)
            } else {
                // Show Error Container
            }
        }
    }
}