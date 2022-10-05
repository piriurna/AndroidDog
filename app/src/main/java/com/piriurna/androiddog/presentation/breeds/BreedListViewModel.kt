package com.piriurna.androiddog.presentation.breeds

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.piriurna.androiddog.ADBaseEventViewModel
import com.piriurna.androiddog.presentation.breeds.models.BreedListEvents
import com.piriurna.androiddog.presentation.breeds.models.BreedListState
import com.piriurna.androiddog.presentation.search.models.SearchBreedsState
import com.piriurna.domain.Resource
import com.piriurna.domain.usecases.LoadDogBreedsUseCase
import com.piriurna.domain.usecases.SearchBreedsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BreedListViewModel @Inject constructor(
    private val loadDogBreedsUseCase: LoadDogBreedsUseCase,
    private val searchBreedsUseCase: SearchBreedsUseCase
) : ADBaseEventViewModel<BreedListEvents>() {

    private val _state: MutableState<BreedListState> = mutableStateOf(BreedListState())
    val state: State<BreedListState> = _state

    init {
        onTriggerEvent(BreedListEvents.GetBreeds)
    }

    override fun onTriggerEvent(event: BreedListEvents) {
        when(event) {

            is BreedListEvents.FetchNewBreeds -> {
                fetchNewBreeds()
            }

            is BreedListEvents.GetBreeds -> {
                getBreeds()
            }

            is BreedListEvents.ChangeListType -> {
                _state.value = _state.value.copy(
                    listType = event.listType
                )
            }
        }
    }



    private fun fetchNewBreeds() {
        loadDogBreedsUseCase().onEach {
            when(it) {
                is Resource.Success -> {
                    getBreeds()
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getBreeds() {
        searchBreedsUseCase("").onEach {
            when(it) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        breedList = it.data!!
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}