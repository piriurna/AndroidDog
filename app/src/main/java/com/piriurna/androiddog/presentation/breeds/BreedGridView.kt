package com.piriurna.androiddog.presentation.breeds

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.piriurna.common.composables.cards.ADCard
import com.piriurna.common.composables.list.ADListRow
import com.piriurna.domain.models.Breed

@Composable
fun BreedGridView(
    modifier: Modifier = Modifier,
    breedList : List<Breed>,
) {
    
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        val columns = breedList.chunked(2)
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            columns.forEach { rowItems ->
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    rowItems.forEach {
                        ADCard(imageUrl = it.image.url, title = it.name, imageHeight = it.getRelativeHeight(150).dp, imageWidth = 150.dp)
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun BreedGridViewPreview() {
    Column(Modifier.fillMaxSize()) {
        BreedGridView(breedList = Breed.mockBreeds)
    }
}