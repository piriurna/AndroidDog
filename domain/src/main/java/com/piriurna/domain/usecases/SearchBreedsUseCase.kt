package com.piriurna.domain.usecases

import com.piriurna.domain.ApiNetworkResponse
import com.piriurna.domain.Resource
import com.piriurna.domain.models.Breed
import com.piriurna.domain.repositories.DogRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class SearchBreedsUseCase @Inject constructor(
    private val dogRepository: DogRepository
) {


    operator fun invoke(query : String) : Flow<Resource<List<Breed>>> = flow {
        emit(Resource.Loading())

        try {
            val dogBreedList : List<Breed> =
                if(query.isEmpty())
                    dogRepository.getBreedsFromDb()
                else
                    dogRepository.getBreedsFromDbWithQuery(query)

            emit(Resource.Success(dogBreedList))
        } catch (e : Exception) {
            emit(Resource.Error("error getting database breeds"))
        }




    }
}