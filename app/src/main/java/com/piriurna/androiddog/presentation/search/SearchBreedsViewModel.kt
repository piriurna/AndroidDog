package com.piriurna.androiddog.presentation.search

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.piriurna.androiddog.ADBaseEventViewModel
import com.piriurna.androiddog.presentation.search.models.SearchBreedsEvents
import com.piriurna.androiddog.presentation.search.models.SearchBreedsState
import com.piriurna.domain.Resource
import com.piriurna.domain.models.Breed
import com.piriurna.domain.usecases.SearchBreedsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import kotlin.reflect.KProperty1

@HiltViewModel
class SearchBreedsViewModel @Inject constructor(
    private val searchBreedsUseCase: SearchBreedsUseCase
) : ADBaseEventViewModel<SearchBreedsEvents>() {


    init {
        onTriggerEvent(SearchBreedsEvents.PerformSearch(""))
    }

    private val _state: MutableState<SearchBreedsState> = mutableStateOf(SearchBreedsState())
    val state: State<SearchBreedsState> = _state

    override fun onTriggerEvent(event: SearchBreedsEvents) {
        when(event) {

            is SearchBreedsEvents.PerformSearch -> {
                performSearch(event.query)
            }

            is SearchBreedsEvents.Sort -> {
                sortList(event.propertyToSort, event.isDescending)
            }
        }
    }


    private fun performSearch(query : String) {
        searchBreedsUseCase(query).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        breedList = result.data?: emptyList(),
                        query = query
                    )
                }
            }
        }.launchIn(viewModelScope)
    }


    private fun sortList(propertyToSort : KProperty1<Breed, String>, isDescending : Boolean) {
        val sortedList = if(isDescending) {
            _state.value.breedList.sortedByDescending(propertyToSort)
        } else {
            _state.value.breedList.sortedBy(propertyToSort)
        }

        _state.value = _state.value.copy(
            breedList = sortedList
        )
    }
}