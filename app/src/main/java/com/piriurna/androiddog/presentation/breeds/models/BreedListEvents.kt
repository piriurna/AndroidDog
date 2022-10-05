package com.piriurna.androiddog.presentation.breeds.models

import com.piriurna.androiddog.ADBaseEvent
import com.piriurna.androiddog.presentation.breedcommons.models.ListType
import com.piriurna.common.models.ListSelectorItem

sealed class BreedListEvents : ADBaseEvent() {

    object FetchNewBreeds : BreedListEvents()

    object GetBreeds : BreedListEvents()

    class ChangeListType(val listType: ListSelectorItem) : BreedListEvents()
}