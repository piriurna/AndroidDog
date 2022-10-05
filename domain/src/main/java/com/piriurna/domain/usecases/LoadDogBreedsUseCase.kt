package com.piriurna.domain.usecases

import com.piriurna.domain.ApiNetworkResponse
import com.piriurna.domain.Resource
import com.piriurna.domain.models.Breed
import com.piriurna.domain.repositories.DogRepository
import com.piriurna.domain.repositories.ImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import java.net.UnknownHostException
import javax.inject.Inject

class LoadDogBreedsUseCase @Inject constructor(
    private val dogRepository: DogRepository,
    private val imageRepository: ImageRepository
) {


    operator fun invoke() : Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())

        try {
            val dogBreedList : ApiNetworkResponse<List<Breed>> = dogRepository.getBreeds()

            dogBreedList.data?.let { breeds ->
                imageRepository.insertImages(breeds.map { it.image })
                dogRepository.insertBreedsIntoDb(breeds)

                emit(Resource.Success(true))
            }?:run {
                handleError(this)
            }
        } catch (e : UnknownHostException) {
            handleError(this)
        }
    }


    private suspend fun handleError(flowCollector: FlowCollector<Resource<Boolean>>) {
        if(dogRepository.getBreedsFromDb().isNotEmpty()) {
            flowCollector.emit(Resource.Success(true))
        } else {
            flowCollector.emit(Resource.Error("Error fetching dog breeds"))
        }
    }
}