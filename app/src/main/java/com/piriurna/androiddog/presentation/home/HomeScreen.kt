package com.piriurna.androiddog.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.piriurna.androiddog.presentation.navigation.HomeNavigationGraph
import com.piriurna.androiddog.presentation.navigation.models.NavigationOptions
import com.piriurna.common.composables.scaffold.ADScaffold

@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController()
) {
    ADScaffold(
        bottomBarItems = listOf(NavigationOptions.AllBreeds, NavigationOptions.SearchBreed),
        onItemSelected = { item ->
            navController.navigate(item.route)
        },
        navController = navController
    ) {
        HomeNavigationGraph(navController = navController)
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}