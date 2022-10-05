package com.piriurna.androiddog.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.piriurna.androiddog.presentation.breeds.BreedDetailScreen
import com.piriurna.androiddog.presentation.navigation.models.Graph

fun NavGraphBuilder.breedNavigationGraph(navController: NavHostController) {

    navigation(
        route = Graph.BREED_GRAPH,
        startDestination = BreedDestinationScreen.BreedDetail.route
    ) {
        composable(
            route = BreedDestinationScreen.BreedDetail.route + "/{breedId}",
            arguments = listOf(
                navArgument("breedId") {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) {
            BreedDetailScreen(
                navController = navController,
                it.arguments?.getString("breedId")!!
            )
        }
    }

}

sealed class BreedDestinationScreen(val route: String) {
    object BreedDetail : BreedDestinationScreen(route = "BREED_DETAIL")
}
