package com.piriurna.data.repository

import com.piriurna.data.database.daos.BreedDao
import com.piriurna.data.mappers.toBreed
import com.piriurna.data.mappers.toBreedEntity
import com.piriurna.data.mappers.toBreedObject
import com.piriurna.data.remote.sources.DogApiSource
import com.piriurna.domain.ApiNetworkResponse
import com.piriurna.domain.repositories.DogRepository
import com.piriurna.domain.models.Breed

class DogRepositoryImpl(
    private val dogApiSource: DogApiSource,
    private val breedDao: BreedDao
): DogRepository {

    override suspend fun getBreeds(): ApiNetworkResponse<List<Breed>> {
        return ApiNetworkResponse(dogApiSource.getBreeds().toBreed())
    }

    override suspend fun insertBreedsIntoDb(breeds: List<Breed>): List<Long> {
        return breedDao.insertBreedList(breeds.toBreedEntity())
    }

    override suspend fun getBreedsFromDb(): List<Breed> {
        return breedDao.getBreedList().toBreedObject()
    }

    override suspend fun getBreedFromDb(id: String): Breed {
        return breedDao.getBreed(id).toBreed()
    }

    override suspend fun getBreedsFromDbWithQuery(queryString: String): List<Breed> {
        return breedDao.getBreedsWithQuery(queryString).toBreedObject()
    }


}