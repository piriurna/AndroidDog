package com.piriurna.domain.usecases

import BaseUseCaseTest
import com.piriurna.domain.ApiNetworkResponse
import com.piriurna.domain.Resource
import com.piriurna.domain.models.Breed
import com.piriurna.domain.repositories.DogRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class LoadDogBreedsUseCaseTest : BaseUseCaseTest() {

    private lateinit var loadDogBreedsUseCase: LoadDogBreedsUseCase
    private lateinit var dogRepository: DogRepository


    @Before
    fun setUp() {
        dogRepository = mock()
        loadDogBreedsUseCase = LoadDogBreedsUseCase(dogRepository = dogRepository)
    }


    @ExperimentalCoroutinesApi
    @Test
    fun `ask for data and receive data`() = runBlockingTest {
        val dogBreeds = listOf(
            Breed(
                name = "Dog Breed 1",
                category = "Friendly, test",
                imageUrl = "",
                temperament = "test",
                origin = "origin"
            ),
            Breed(
                name = "Dog Breed 2",
                category = "Friendly, test",
                imageUrl = "",
                temperament = "test",
                origin = "origin"
            ),
            Breed(
                name = "Dog Breed 3",
                category = "Friendly, test",
                imageUrl = "",
                temperament = "test",
                origin = "origin"
            )
        )


        whenever(dogRepository.getBreeds()).thenReturn(
            ApiNetworkResponse(dogBreeds)
        )


        val emissions = loadDogBreedsUseCase().toList()
        var result = emissions[0]

        assert(result is Resource.Loading)

        result = emissions[1]

        assert(result is Resource.Success)

        verify(dogRepository, times(1)).getBreeds()


    }
}