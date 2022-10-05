package com.piriurna.androiddog.presentation.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.*
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.piriurna.androiddog.presentation.navigation.HomeDestinationScreen
import com.piriurna.androiddog.presentation.navigation.RootDestinationScreen
import com.piriurna.androiddog.presentation.splash.models.SplashEvents
import com.piriurna.androiddog.presentation.splash.models.SplashState
import com.piriurna.common.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavHostController = rememberNavController()
) {

    val viewModel : SplashViewModel = hiltViewModel()

    val state = viewModel.state.value

    LaunchedEffect(Unit) {
        viewModel.onTriggerEvent(SplashEvents.LoadBreeds)
    }

    BuildSplashScreen(navController = navController, state = state, events = viewModel::onTriggerEvent)
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BuildSplashScreen(
    navController: NavHostController,
    state : SplashState,
    events: (SplashEvents) -> Unit
) {
    val error by derivedStateOf {
        !state.success
    }

    LaunchedEffect(key1 = state) {
        if(state.success) {
            navController.navigate(RootDestinationScreen.Home.route)
        }
    }

    val infiniteTransition = rememberInfiniteTransition()

    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500),
            repeatMode = RepeatMode.Restart
        )
    )

    AnimatedVisibility(
        visible = state.isLoading,
        exit = scaleOut(),
        enter = scaleIn()
    ) {

        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth(0.5f)
                    .fillMaxHeight(0.5f)
                    .graphicsLayer {
                        this.rotationZ = angle
                    },
                painter = painterResource(id = R.drawable.ic_dog),
                contentDescription = "App Logo"
            )

        }
    }
    AnimatedVisibility(
        visible = error && !state.isLoading,
        exit = scaleOut(),
        enter = scaleIn()
    ) {

        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Image(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .fillMaxHeight(0.5f),
                painter = painterResource(id = R.drawable.ic_error),
                contentDescription = "Error No Dogs"
            )

            Text(text = "Error!", textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = 30.sp)

            Text(modifier = Modifier.padding(top = 8.dp), text = "No internet connection...", textAlign = TextAlign.Center, fontSize = 22.sp)

            Button(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(0.7f),
                onClick = { events(SplashEvents.LoadBreeds) }
            ) {
                Text(text = "Retry")
            }

        }
    }

}

@Preview
@Composable
fun SplashScreenPreview() {
    BuildSplashScreen(rememberNavController(), state = SplashState(), events = {})
}