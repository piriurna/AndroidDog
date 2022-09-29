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
fun BreedListView(
    modifier: Modifier = Modifier,
    breedList : List<Breed>
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            breedList.forEach {
                ADListRow(imageUrl = it.image.url, title = it.name, imageHeight = it.getRelativeHeight(80).dp, imageWidth = 80.dp)
            }
        }

    }
}


@Preview
@Composable
fun BreedListViewPreview() {
    Column(Modifier.fillMaxSize()) {
        BreedListView(breedList = Breed.mockBreeds)
    }
}