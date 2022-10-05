package com.piriurna.androiddog.presentation.breeds

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.piriurna.androiddog.presentation.breedcommons.BreedsShowContainer
import com.piriurna.androiddog.presentation.breeds.models.BreedListEvents
import com.piriurna.androiddog.presentation.breeds.models.BreedListState
import com.piriurna.common.composables.list.ADGridListSelector
import com.piriurna.common.composables.scaffold.ADScaffold


@Composable
fun BreedListScreen(
    navController: NavController
) {

    val viewModel : BreedListViewModel = hiltViewModel()

    val state = viewModel.state.value

    BuildBreedListScreen(state = state, events = viewModel::onTriggerEvent, navController = navController)
}

@Composable
fun BuildBreedListScreen(
    navController : NavController,
    state : BreedListState,
    events : (BreedListEvents) -> Unit
) {

    ADScaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "All Breeds", fontWeight = FontWeight.Bold, fontSize = 30.sp, color = Color.White)


                ADGridListSelector(
                    onOptionSelected = { events(BreedListEvents.ChangeListType(it)) },
                    selectedOption = state.listType
                )
            }
        }
    ) {
        BreedsShowContainer(
            navController = navController,
            breedList = state.breedList,
            listType = state.listType,
        )
    }
}


@Preview(showBackground = true)
@Composable
fun BreedListScreenPreview() {
    BuildBreedListScreen(state = BreedListState(), events = {}, navController = rememberNavController())
}