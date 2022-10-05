package com.piriurna.androiddog.presentation.splash.models

import com.piriurna.androiddog.ADBaseEvent

sealed class SplashEvents : ADBaseEvent() {

    object LoadBreeds : SplashEvents()
}