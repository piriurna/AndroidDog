package com.piriurna.androiddog.presentation.breeds

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.piriurna.androiddog.presentation.breeddetail.BreedDetailEvents
import com.piriurna.androiddog.presentation.breeddetail.BreedDetailViewModel
import com.piriurna.androiddog.presentation.breeddetail.models.BreedDetailState
import com.piriurna.common.composables.images.ADAsyncImage
import com.piriurna.common.composables.scaffold.ADScaffold

@Composable
fun BreedDetailScreen(
    navController: NavController,
    breedId : String
) {

    val viewModel : BreedDetailViewModel = hiltViewModel()

    val state = viewModel.state.value

    LaunchedEffect(Unit) {
        viewModel.onTriggerEvent(BreedDetailEvents.GetBreed(breedId))
    }

    BuildBreedDetailScreen(state = state, events = viewModel::onTriggerEvent, navController = navController)
}


@Composable
fun BuildBreedDetailScreen(
    navController: NavController,
    state : BreedDetailState,
    events : (BreedDetailEvents) -> Unit
) {

    val breed = state.breed
    ADScaffold(
        topBar = {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Image(
                    modifier = Modifier.align(Alignment.CenterStart).padding(start = 8.dp).clickable { navController.popBackStack() },
                    imageVector = Icons.Default.ArrowBack,
                    colorFilter = ColorFilter.tint(Color.White),
                    contentDescription = "Back Button"
                )

                Text(
                    modifier = Modifier.align(Alignment.Center).fillMaxWidth(0.85f),
                    text = state.breed?.name?:"",
                    fontSize = if((state.breed?.name?.length ?: 0) < 18) 25.sp else 20.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }

        }
    ) {
        if(breed != null) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                ADAsyncImage(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    imageUrl = state.breed.image.url,
                    contentDescription = "Breed Image"
                )

                val category = breed.category.ifEmpty { "---" }
                Text(text = category, fontSize = 20.sp, textAlign = TextAlign.Center)

                val origin = breed.origin.ifEmpty { "---" }
                Text(text = origin, fontSize = 20.sp, textAlign = TextAlign.Center)

                val temperament = breed.temperament.ifEmpty { "---" }
                Text(text = temperament, fontSize = 20.sp, textAlign = TextAlign.Center)
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun BreedDetailScreenPreview() {

}