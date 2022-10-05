package com.piriurna.androiddog.presentation.breedcommons

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.piriurna.common.composables.list.ADGridListSelector
import com.piriurna.common.models.ListSelectorItem
import com.piriurna.domain.models.Breed

@Composable
fun BreedsShowContainer(
    navController: NavController,
    breedList : List<Breed>,
    listType : ListSelectorItem = ListSelectorItem.GRID,
) {

    Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
        if (listType == ListSelectorItem.LIST) {
            BreedListView(navController= navController, breedList = breedList)
        } else {
            BreedGridView(breedList = breedList, navController = navController)
        }
    }
}