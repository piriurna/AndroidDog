package com.piriurna.androiddog.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.piriurna.androiddog.presentation.home.HomeScreen
import com.piriurna.androiddog.presentation.navigation.models.Graph

@Composable
fun HomeNavigationGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        route = Graph.HOME_GRAPH,
        startDestination = HomeDestinationScreen.BreedList.route
    ) {
        composable(route = HomeDestinationScreen.BreedList.route) {
            HomeScreen()
        }

        composable(route = HomeDestinationScreen.BreedSearch.route) {
//            SplashScreen(navController = navController)
        }
    }

}

sealed class HomeDestinationScreen(val route: String) {
    object BreedList : RootDestinationScreen(route = "BREED_LIST")
    object BreedSearch : RootDestinationScreen(route = "BREED_SEARCH")
}
