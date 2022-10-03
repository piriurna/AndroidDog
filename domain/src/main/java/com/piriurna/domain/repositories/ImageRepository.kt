package com.piriurna.domain.repositories

import com.piriurna.domain.ApiNetworkResponse
import com.piriurna.domain.models.Breed
import com.piriurna.domain.models.BreedImage

interface ImageRepository {


    suspend fun insertImages(images : List<BreedImage>) : List<Long>

}