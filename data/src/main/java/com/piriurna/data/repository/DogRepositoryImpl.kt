package com.piriurna.data.repository

import com.piriurna.data.mappers.toBreed
import com.piriurna.data.remote.sources.DogApiSource
import com.piriurna.domain.ApiNetworkResponse
import com.piriurna.domain.repositories.DogRepository
import com.piriurna.domain.models.Breed

class DogRepositoryImpl(
    private val dogApiSource: DogApiSource
): DogRepository {

    override suspend fun getBreeds(): ApiNetworkResponse<List<Breed>> {
        return ApiNetworkResponse(dogApiSource.getBreeds().toBreed())
    }
}