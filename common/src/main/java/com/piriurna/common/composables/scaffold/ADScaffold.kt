package com.piriurna.common.composables.scaffold

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.piriurna.common.models.BottomNavigationItem


@Composable
fun ADScaffold(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    bottomBarItems: List<BottomNavigationItem> = emptyList(),
    onItemSelected: (BottomNavigationItem) -> Unit = {},
    navController: NavHostController? = null,
    content: @Composable (PaddingValues) -> Unit,
) {


    Box(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
    ) {
        val scaffoldModifier = modifier.fillMaxSize()
        Scaffold(
            modifier = scaffoldModifier,
            content = content,
            topBar = {

            },
            bottomBar = {
                navController?.let { navController ->
                    if(bottomBarItems.isNotEmpty()) {
                        ADBottomNavigation(
                            selectedColor = Color.Black,
                            unselectedColor = Color.LightGray,
                            items = bottomBarItems,
                            selectedRoute = bottomBarItems[0].route,
                            onItemSelected = onItemSelected,
                            navController = navController
                        )
                    }
                }
            }
        )

//        SQLoading(isLoading = isLoading)
    }
}


@Preview(showBackground = true)
@Composable
fun ADScaffoldPreview() {
    ADScaffold(
        isLoading = true,
        bottomBarItems = listOf(),
    ) {
        Text(text = "Scaffold test")
    }
}