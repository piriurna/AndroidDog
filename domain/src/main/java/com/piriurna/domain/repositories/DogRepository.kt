package com.piriurna.domain.repositories

import com.piriurna.domain.ApiNetworkResponse
import com.piriurna.domain.models.Breed

interface DogRepository {


    suspend fun getBreeds() : ApiNetworkResponse<List<Breed>>
}