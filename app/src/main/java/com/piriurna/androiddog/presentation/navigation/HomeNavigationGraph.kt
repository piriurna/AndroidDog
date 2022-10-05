package com.piriurna.androiddog.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.piriurna.androiddog.presentation.breeds.BreedListScreen
import com.piriurna.androiddog.presentation.home.HomeScreen
import com.piriurna.androiddog.presentation.navigation.models.Graph
import com.piriurna.androiddog.presentation.navigation.models.NavigationOptions
import com.piriurna.androiddog.presentation.search.SearchBreedsScreen

@Composable
fun HomeNavigationGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        route = Graph.HOME_GRAPH,
        startDestination = HomeDestinationScreen.BreedList.route
    ) {

        breedNavigationGraph(navController)

        composable(route = HomeDestinationScreen.BreedList.route) {
            BreedListScreen(navController)
        }

        composable(route = HomeDestinationScreen.BreedSearch.route) {
            SearchBreedsScreen(navController = navController)
        }
    }

}

sealed class HomeDestinationScreen(val route: String) {
    object BreedList : HomeDestinationScreen(route = NavigationOptions.AllBreeds.route)
    object BreedSearch : HomeDestinationScreen(route = NavigationOptions.SearchBreed.route)
}
