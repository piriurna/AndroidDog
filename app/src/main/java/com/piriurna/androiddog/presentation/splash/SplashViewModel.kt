package com.piriurna.androiddog.presentation.splash

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.piriurna.androiddog.ADBaseEventViewModel
import com.piriurna.androiddog.presentation.search.models.SearchBreedsState
import com.piriurna.androiddog.presentation.splash.models.SplashEvents
import com.piriurna.androiddog.presentation.splash.models.SplashState
import com.piriurna.domain.Resource
import com.piriurna.domain.usecases.LoadDogBreedsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val loadDogBreedsUseCase: LoadDogBreedsUseCase
) : ADBaseEventViewModel<SplashEvents>() {

    private val _state: MutableState<SplashState> = mutableStateOf(SplashState())
    val state: State<SplashState> = _state

    override fun onTriggerEvent(event: SplashEvents) {
        when(event) {
            is SplashEvents.LoadBreeds -> {
                loadBreeds()
            }
        }
    }


    private fun loadBreeds() {
        loadDogBreedsUseCase().onEach { result ->
            when(result) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true
                    )
                }
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        success = true
                    )
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        success = false
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}