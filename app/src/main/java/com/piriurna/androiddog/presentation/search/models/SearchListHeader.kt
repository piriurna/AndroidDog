package com.piriurna.androiddog.presentation.search.models

import com.piriurna.domain.models.Breed

data class SearchListHeader(
    val title : String,
    val onClick : () -> Unit
)
