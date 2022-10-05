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

class LoadDogBreedsUseCaseTest : BaseUseCaseTest() {

    private lateinit var loadDogBreedsUseCase: LoadDogBreedsUseCase
    private lateinit var dogRepository: DogRepository
    private lateinit var imageRepository: ImageRepository

    @Before
    fun setUp() {
        dogRepository = mock()
        imageRepository = mock()
        loadDogBreedsUseCase = LoadDogBreedsUseCase(dogRepository = dogRepository, imageRepository = imageRepository)
    }


    @ExperimentalCoroutinesApi
    @Test
    fun `ask for data and receive data`() = runBlockingTest {
        val dogBreeds = listOf(
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


        whenever(dogRepository.getBreeds()).thenReturn(
            ApiNetworkResponse(dogBreeds)
        )


        val emissions = loadDogBreedsUseCase().toList()
        var result = emissions[0]

        assert(result is Resource.Loading)

        result = emissions[1]

        assert(result is Resource.Success)

        verify(dogRepository, times(1)).getBreeds()
        verify(imageRepository, times(1)).insertImages(BreedImage.mockImages)

    }

    @ExperimentalCoroutinesApi
    @Test
    fun `ask for data get network error but get database data get success`() = runBlockingTest {
        val databaseDogBreeds = listOf(
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


        whenever(dogRepository.getBreeds()).thenReturn(
            ApiNetworkResponse(error = ApiNetworkError(500, "Error fetching dog breeds"))
        )

        whenever(dogRepository.getBreedsFromDb()).thenReturn(
            databaseDogBreeds
        )


        val emissions = loadDogBreedsUseCase().toList()
        var result = emissions[0]

        assert(result is Resource.Loading)

        result = emissions[1]

        assert(result is Resource.Success)

        verify(dogRepository, times(1)).getBreeds()
        verify(dogRepository, times(1)).getBreedsFromDb()

    }


    @ExperimentalCoroutinesApi
    @Test
    fun `ask for data get network error and no database data get error`() = runBlockingTest {
        val databaseDogBreeds = emptyList<Breed>()


        whenever(dogRepository.getBreeds()).thenReturn(
            ApiNetworkResponse(error = ApiNetworkError(500, "Error fetching dog breeds"))
        )

        whenever(dogRepository.getBreedsFromDb()).thenReturn(
            databaseDogBreeds
        )


        val emissions = loadDogBreedsUseCase().toList()
        var result = emissions[0]

        assert(result is Resource.Loading)

        result = emissions[1]

        assert(result is Resource.Error)

        verify(dogRepository, times(1)).getBreeds()
        verify(dogRepository, times(1)).getBreedsFromDb()

    }
}