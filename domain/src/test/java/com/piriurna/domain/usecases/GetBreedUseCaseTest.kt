package com.piriurna.domain.usecases

import BaseUseCaseTest
import com.piriurna.domain.ApiNetworkError
import com.piriurna.domain.ApiNetworkResponse
import com.piriurna.domain.Resource
import com.piriurna.domain.models.Breed
import com.piriurna.domain.models.BreedImage
import com.piriurna.domain.repositories.DogRepository
import com.piriurna.domain.repositories.ImageRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GetBreedUseCaseTest : BaseUseCaseTest() {

    private lateinit var getBreedUseCase: GetBreedUseCase
    private lateinit var dogRepository: DogRepository

    @Before
    fun setUp() {
        dogRepository = mock()
        getBreedUseCase = GetBreedUseCase(dogRepository = dogRepository)
    }


    @ExperimentalCoroutinesApi
    @Test
    fun `ask for data and receive data`() = runBlockingTest {
       val breed = Breed(
           id = 0,
           name = "Dog Breed 1",
           category = "Friendly, test",
           temperament = "test",
           origin = "origin",
           image = BreedImage.mockImages[0]
       )

        whenever(dogRepository.getBreedFromDb(breed.id.toString())).thenReturn(
            breed
        )

        val emissions = getBreedUseCase(breed.id.toString()).toList()
        var result = emissions[0]

        assert(result is Resource.Loading)

        result = emissions[1]

        assert(result is Resource.Success)

        verify(dogRepository, times(1)).getBreedFromDb(breed.id.toString())

    }
}