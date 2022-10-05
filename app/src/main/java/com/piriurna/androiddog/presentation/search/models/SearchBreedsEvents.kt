package com.piriurna.androiddog.presentation.search.models

import com.piriurna.androiddog.ADBaseEvent
import com.piriurna.domain.models.Breed
import kotlin.reflect.KProperty1

sealed class SearchBreedsEvents : ADBaseEvent(){

    class PerformSearch(val query : String) : SearchBreedsEvents()

    class Sort(val propertyToSort : KProperty1<Breed, String>, val isDescending : Boolean) : SearchBreedsEvents()
}