package com.piriurna.common.composables.scaffold

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.piriurna.common.models.BottomNavigationItem
import kotlin.math.roundToInt


@Composable
fun ADScaffold(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    bottomBarItems: List<BottomNavigationItem> = emptyList(),
    onItemSelected: (BottomNavigationItem) -> Unit = {},
    topBar : @Composable (() -> Unit)? = null,
    navController: NavHostController? = null,
    content: @Composable (PaddingValues) -> Unit,
) {
    val bottomBarHeight = 55.dp
    val bottomBarHeightPx = with(LocalDensity.current) {
        bottomBarHeight.roundToPx().toFloat()
    }
    val bottomBarOffsetHeightPx = remember { mutableStateOf(0f) }
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                val delta = available.y
                val newOffset = bottomBarOffsetHeightPx.value + delta
                bottomBarOffsetHeightPx.value =
                    newOffset.coerceIn(-bottomBarHeightPx, 0f)
                return Offset.Zero
            }
        }
    }

    Box(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
    ) {
        val scaffoldModifier = modifier.fillMaxSize()
        Scaffold(
            modifier = scaffoldModifier.nestedScroll(nestedScrollConnection),
            content = {
                content(it)
            },
            topBar = {
                if(topBar != null) {
                    TopAppBar {
                        topBar()
                    }
                }
            },
            bottomBar = {
                navController?.let { navController ->
                    if(bottomBarItems.isNotEmpty()) {
                        ADBottomNavigation(
                            modifier = Modifier
                                .height(bottomBarHeight)
                                .offset {
                                    IntOffset(
                                        x = 0,
                                        y = -bottomBarOffsetHeightPx.value.roundToInt()
                                    )
                                },
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