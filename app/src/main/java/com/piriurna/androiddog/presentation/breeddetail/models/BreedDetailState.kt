package com.piriurna.androiddog.presentation.breeddetail.models

import com.piriurna.domain.models.Breed

data class BreedDetailState(
    val isLoading : Boolean = false,
    val breed: Breed? = null,
    val error : String? = null
) {
}