package com.piriurna.data.remote.sources

import com.piriurna.data.BuildConfig
import com.piriurna.data.remote.DogApi
import com.piriurna.data.remote.HandleApi.safeApiCall
import com.piriurna.data.remote.dto.BreedDto
import javax.inject.Inject

class DogApiSource @Inject constructor(
    private val dogApi: DogApi
) {

    suspend fun getBreeds() : List<BreedDto> {
        return safeApiCall { dogApi.getBreeds(BuildConfig.API_KEY) }
    }
}