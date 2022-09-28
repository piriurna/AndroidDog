package com.piriurna.domain.repositories

import com.piriurna.domain.ApiNetworkResponse
import com.piriurna.domain.models.Breed

interface DogRepository {


    suspend fun getBreeds() : ApiNetworkResponse<List<Breed>>

    suspend fun insertBreedsIntoDb(breeds : List<Breed>) : List<Long>

    suspend fun getBreedsFromDb() : List<Breed>

    suspend fun getBreedsFromDbWithQuery(queryString : String) : List<Breed>
}