package com.piriurna.androiddog.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.piriurna.androiddog.presentation.home.HomeScreen
import com.piriurna.androiddog.presentation.navigation.models.Graph

@Composable
fun RootNavigationGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        route = Graph.ROOT_GRAPH,
        startDestination = RootDestinationScreen.Splash.route
    ) {
        composable(route = RootDestinationScreen.Home.route) {
            HomeScreen()
        }

        composable(route = RootDestinationScreen.Splash.route) {
//            SplashScreen(navController = navController)
        }
    }

}

sealed class RootDestinationScreen(val route: String) {
    object Home : RootDestinationScreen(route = "HOME")
    object Splash : RootDestinationScreen(route = "SPLASH")
}
