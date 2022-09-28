package com.piriurna.data.remote

import com.piriurna.data.remote.dto.BreedDto
import retrofit2.http.GET

interface DogApi {


    @GET("breeds")
    suspend fun getBreeds() : List<BreedDto>

}