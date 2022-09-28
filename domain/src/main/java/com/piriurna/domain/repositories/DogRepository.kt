package com.piriurna.domain.repositories

import com.piriurna.domain.models.Breed

interface DogRepository {


    suspend fun getBreeds() : List<Breed>
}