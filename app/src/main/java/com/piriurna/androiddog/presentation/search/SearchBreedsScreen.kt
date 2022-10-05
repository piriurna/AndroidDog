package com.piriurna.androiddog.presentation.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.piriurna.androiddog.presentation.breedcommons.BreedListView
import com.piriurna.androiddog.presentation.breedcommons.models.ListType
import com.piriurna.androiddog.presentation.search.models.SearchBreedsEvents
import com.piriurna.androiddog.presentation.search.models.SearchBreedsState
import com.piriurna.androiddog.presentation.search.models.SearchListHeader
import com.piriurna.common.R
import com.piriurna.common.composables.scaffold.ADScaffold
import com.piriurna.domain.models.Breed

@Composable
fun SearchBreedsScreen(
    navController: NavController
) {

    val viewModel : SearchBreedsViewModel = hiltViewModel()

    val state = viewModel.state.value

    BuildSearchBreedsScreen(navController = navController, state = state) { viewModel.onTriggerEvent(it) }
}

@Composable
fun BuildSearchBreedsScreen(
    navController: NavController,
    state : SearchBreedsState,
    events : (SearchBreedsEvents) -> Unit
) {
    var searchQuery by remember{
        mutableStateOf(state.query)
    }

    var breedNameSorting by remember {
        mutableStateOf<Boolean>(false)
    }

    var categorySorting by remember {
        mutableStateOf<Boolean>(false)
    }

    var originSorting by remember {
        mutableStateOf<Boolean>(false)
    }

    LaunchedEffect(searchQuery) {
        events.invoke(SearchBreedsEvents.PerformSearch(searchQuery))
    }

    ADScaffold(
        topBar = {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                },
                trailingIcon = {
                    Image(painter = painterResource(id = R.drawable.ic_search), contentDescription = "Search", colorFilter = ColorFilter.tint(Color.White))
                },
                shape = RoundedCornerShape(50),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
        }
    ) {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.spacedBy(8.dp)) {
            val configuration = LocalConfiguration.current

            val screenWidth = configuration.screenWidthDp
            val fieldsSize = (screenWidth / 3.2).dp
            val fieldsMap = listOf(
                SearchListHeader("Breed Name") {
                    events.invoke(SearchBreedsEvents.Sort(Breed::name, breedNameSorting))
                    breedNameSorting = !breedNameSorting
                },
                SearchListHeader("Category") {
                    events.invoke(SearchBreedsEvents.Sort(Breed::category, !categorySorting))
                    categorySorting = !categorySorting
                },
                SearchListHeader("Origin") {
                    events.invoke(SearchBreedsEvents.Sort(Breed::origin, !originSorting))
                    originSorting = !originSorting
                },
            )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Card(elevation = 8.dp) {
                        Row {
                            fieldsMap.forEach {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .clickable { it.onClick() }
                                ) {
                                    Text(
                                        modifier = Modifier.width(fieldsSize),
                                        text = it.title,
                                        overflow = TextOverflow.Ellipsis,
                                        maxLines = 1,
                                        textAlign = TextAlign.Center
                                    )

                                    Divider(
                                        modifier = Modifier
                                            .height(32.dp)
                                            .width(1.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            BreedListView(breedList = state.breedList, listType = ListType.FIELDS, fieldsSize = fieldsSize, navController = navController)
        }
    }


}




@Preview(showBackground = true)
@Composable
fun SearchBreedsScreenPreview() {
    BuildSearchBreedsScreen(
        state = SearchBreedsState(breedList = Breed.mockBreeds),
        events = {},
        navController = rememberNavController()
    )
}