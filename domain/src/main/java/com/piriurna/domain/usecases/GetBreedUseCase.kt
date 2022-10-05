package com.piriurna.domain.usecases

import com.piriurna.domain.ApiNetworkResponse
import com.piriurna.domain.Resource
import com.piriurna.domain.models.Breed
import com.piriurna.domain.repositories.DogRepository
import com.piriurna.domain.repositories.ImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GetBreedUseCase @Inject constructor(
    private val dogRepository: DogRepository
) {


    operator fun invoke(id : String) : Flow<Resource<Breed>> = flow {
        emit(Resource.Loading())

        try {
            val breed : Breed = dogRepository.getBreedFromDb(id)

            emit(Resource.Success(breed))
        } catch (e : Exception) {
            emit(Resource.Error("Error getting breed"))
        }
    }
}