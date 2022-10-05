package com.piriurna.data.remote

import com.piriurna.data.remote.dto.BreedDto
import retrofit2.http.GET
import retrofit2.http.Header

interface DogApi {


    @GET("breeds")
    suspend fun getBreeds(
        @Header("x-api-key") apiKey : String
    ) : List<BreedDto>

}