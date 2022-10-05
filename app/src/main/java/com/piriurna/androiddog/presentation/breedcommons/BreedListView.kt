package com.piriurna.androiddog.presentation.breedcommons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.piriurna.androiddog.presentation.breedcommons.models.ListType
import com.piriurna.androiddog.presentation.navigation.BreedDestinationScreen
import com.piriurna.common.composables.list.ADImageTitleListRow
import com.piriurna.common.composables.list.ADListRow
import com.piriurna.domain.models.Breed

@Composable
fun BreedListView(
    navController: NavController,
    modifier: Modifier = Modifier,
    breedList : List<Breed>,
    listType : ListType = ListType.IMAGE_TITLE,
    fieldsSize : Dp = 100.dp
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start,
    ) {
        items(breedList.size) { index ->
            val breed = breedList[index]
            if(listType == ListType.IMAGE_TITLE) {
                ADImageTitleListRow(
                    modifier = Modifier.clickable { navController.navigate(BreedDestinationScreen.BreedDetail.route + "/${breed.id}") },
                    imageUrl = breed.image.url,
                    title = breed.name,
                    imageHeight = 60.dp,
                    imageWidth = 80.dp
                )
            } else {
                ADListRow(
                    modifier = Modifier
                        .background(if(index % 2 != 0) Color.LightGray else Color.Transparent)
                        .clickable { navController.navigate(BreedDestinationScreen.BreedDetail.route + "/${breed.id}") },
                    fields = listOf(breed.name, breed.category, breed.origin),
                    fieldSize = fieldsSize
                )
            }
        }
    }
}


@Preview
@Composable
fun BreedListViewPreview() {
    Column(Modifier.fillMaxSize()) {
        BreedListView(navController = rememberNavController(), breedList = Breed.mockBreeds)
    }
}