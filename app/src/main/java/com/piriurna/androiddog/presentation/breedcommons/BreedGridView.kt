package com.piriurna.androiddog.presentation.breedcommons

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.piriurna.androiddog.presentation.navigation.BreedDestinationScreen
import com.piriurna.common.composables.cards.ADCard
import com.piriurna.domain.models.Breed

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BreedGridView(
    modifier: Modifier = Modifier,
    breedList : List<Breed>,
    navController: NavController
) {

    val configuration = LocalConfiguration.current

    val screenWidth = configuration.screenWidthDp
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        LazyVerticalGrid(cells = GridCells.Adaptive((screenWidth / 2.5f).dp), verticalArrangement = Arrangement.spacedBy(8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp), contentPadding = PaddingValues(horizontal = 8.dp)) {
            items(breedList.size) { index ->
                val breed = breedList[index]
                ADCard(modifier = Modifier.width(100.dp),imageUrl = breed.image.url, title = breed.name, imageHeight = 100.dp, imageWidth = (screenWidth / 3f).dp, onClick = { navController.navigate(BreedDestinationScreen.BreedDetail.route + "/${breed.id}") })
            }
        }
    }
}


@Preview
@Composable
fun BreedGridViewPreview() {
    Column(Modifier.fillMaxSize()) {
        BreedGridView(breedList = Breed.mockBreeds, navController = rememberNavController())
    }
}