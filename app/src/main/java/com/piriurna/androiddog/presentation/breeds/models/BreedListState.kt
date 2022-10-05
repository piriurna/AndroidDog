package com.piriurna.androiddog.presentation.breeds.models

import com.piriurna.common.models.ListSelectorItem
import com.piriurna.domain.models.Breed

data class BreedListState(
    val isLoading : Boolean = false,
    val breedList : List<Breed> = emptyList(),
    val listType : ListSelectorItem = ListSelectorItem.GRID
) {
}