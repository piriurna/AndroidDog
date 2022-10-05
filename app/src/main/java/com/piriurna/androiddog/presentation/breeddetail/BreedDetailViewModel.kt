package com.piriurna.androiddog.presentation.breeddetail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.piriurna.androiddog.ADBaseEventViewModel
import com.piriurna.androiddog.presentation.breeddetail.models.BreedDetailState
import com.piriurna.androiddog.presentation.breeds.models.BreedListState
import com.piriurna.domain.Resource
import com.piriurna.domain.usecases.GetBreedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BreedDetailViewModel @Inject constructor(
    private val getBreedUseCase: GetBreedUseCase
) : ADBaseEventViewModel<BreedDetailEvents>() {


    private val _state: MutableState<BreedDetailState> = mutableStateOf(BreedDetailState())
    val state: State<BreedDetailState> = _state

    override fun onTriggerEvent(event: BreedDetailEvents) {
        when(event) {
            is BreedDetailEvents.GetBreed -> {
                getBreed(event.id)
            }
        }
    }



    private fun getBreed(id : String) {
        getBreedUseCase(id).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        breed = result.data
                    )
                }

                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true
                    )
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = result.message
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}