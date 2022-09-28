package com.piriurna.domain.usecases

import com.piriurna.domain.ApiNetworkResponse
import com.piriurna.domain.Resource
import com.piriurna.domain.models.Breed
import com.piriurna.domain.repositories.DogRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoadDogBreedsUseCase @Inject constructor(
    private val dogRepository: DogRepository
) {


    operator fun invoke() : Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())

        val dogBreedList : ApiNetworkResponse<List<Breed>> = dogRepository.getBreeds()

        dogBreedList.data?.let { breeds ->

            dogRepository.insertBreedsIntoDb(breeds)

            emit(Resource.Success(true))
        }?:run {
            if(dogRepository.getBreedsFromDb().isNotEmpty()) {
                emit(Resource.Success(true))
            } else {
                emit(Resource.Error("Error fetching dog breeds"))
            }
        }
    }
}