package com.piriurna.androiddog.presentation.navigation.models

import com.piriurna.common.R
import com.piriurna.common.models.BottomNavigationItem

object NavigationOptions {
    object AllBreeds : BottomNavigationItem(route = "BREED_LIST", iconId = R.drawable.ic_dog, title = "All breeds")

    object SearchBreed : BottomNavigationItem(route = "SEARCH_BREED", iconId = R.drawable.ic_search, title = "Search breed")
}
