package com.piriurna.androiddog.presentation.breeds

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.piriurna.androiddog.presentation.navigation.models.NavigationOptions
import com.piriurna.common.composables.list.ADGridListSelector
import com.piriurna.common.composables.scaffold.ADScaffold
import com.piriurna.common.models.ListSelectorItem
import com.piriurna.domain.models.Breed

@Composable
fun BreedListScreen(
    navController : NavHostController
) {
    val breedList = Breed.mockBreeds
    
    var listType by remember {
        mutableStateOf(ListSelectorItem.LIST)
    }

    ADScaffold() {

        Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "All breeds", fontWeight = FontWeight.Bold, fontSize = 30.sp)

                ADGridListSelector(
                    onOptionSelected = {
                        listType = it
                    },
                    selectedOption = listType
                )
            }

            if (listType == ListSelectorItem.LIST) {
                BreedListView(breedList = breedList)
            } else {
                BreedGridView(breedList = breedList)
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun BreedListScreenPreview() {
    BreedListScreen(rememberNavController())
}