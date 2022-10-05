package com.piriurna.androiddog.presentation.breeddetail

import com.piriurna.androiddog.ADBaseEvent
import com.piriurna.androiddog.presentation.breeddetail.models.BreedDetailState

sealed class BreedDetailEvents : ADBaseEvent(){

    class GetBreed(val id : String) : BreedDetailEvents()
}
