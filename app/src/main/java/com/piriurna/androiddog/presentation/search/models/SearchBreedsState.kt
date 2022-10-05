package com.piriurna.androiddog.presentation.search.models

import com.piriurna.domain.models.Breed

data class SearchBreedsState(
    val isLoading : Boolean = false,
    val breedList : List<Breed> = emptyList(),
    val query : String = ""
) {
}