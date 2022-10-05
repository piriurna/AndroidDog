package com.piriurna.domain.usecases

import BaseUseCaseTest
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

class SearchBreedsUseCaseTest : BaseUseCaseTest() {

    private lateinit var searchBreedsUseCase: SearchBreedsUseCase
    private lateinit var dogRepository: DogRepository

    private val breedList = listOf(
        Breed(
            id = 0,
            name = "Dog Breed 1",
            category = "Friendly, test",
            temperament = "test",
            origin = "origin",
            image = BreedImage.mockImages[0]
        ),
        Breed(
            id = 1,
            name = "Dog Breed 2",
            category = "Friendly, test",
            temperament = "test",
            origin = "origin",
            image = BreedImage.mockImages[1]
        ),
        Breed(
            id = 2,
            name = "Dog Breed 3",
            category = "Friendly, test",
            temperament = "test",
            origin = "origin",
            image = BreedImage.mockImages[2]
        )

    )

    @Before
    fun setUp() {
        dogRepository = mock()
        searchBreedsUseCase = SearchBreedsUseCase(dogRepository = dogRepository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `ask for data and receive data`() = runBlockingTest {
        whenever(dogRepository.getBreedsFromDbWithQuery("1")).thenReturn(
            listOf(breedList[0])
        )

        val emissions = searchBreedsUseCase("1").toList()
        var result = emissions[0]

        assert(result is Resource.Loading)

        result = emissions[1]

        assert(result is Resource.Success)

        verify(dogRepository, times(1)).getBreedsFromDbWithQuery("1")

    }

    @ExperimentalCoroutinesApi
    @Test
    fun `ask for data with empty query and receive data`() = runBlockingTest {

        whenever(dogRepository.getBreedsFromDbWithQuery("")).thenReturn(
            breedList
        )

        val emissions = searchBreedsUseCase("").toList()
        var result = emissions[0]

        assert(result is Resource.Loading)

        result = emissions[1]

        assert(result is Resource.Success)

        verify(dogRepository, times(1)).getBreedsFromDb()

    }
}