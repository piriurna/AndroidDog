package com.piriurna.data.repository

import com.piriurna.data.database.daos.BreedDao
import com.piriurna.data.database.daos.ImageDao
import com.piriurna.data.mappers.toBreed
import com.piriurna.data.mappers.toBreedEntity
import com.piriurna.data.mappers.toBreedObject
import com.piriurna.data.mappers.toImageEntity
import com.piriurna.data.remote.sources.DogApiSource
import com.piriurna.domain.ApiNetworkResponse
import com.piriurna.domain.repositories.DogRepository
import com.piriurna.domain.models.Breed
import com.piriurna.domain.models.BreedImage
import com.piriurna.domain.repositories.ImageRepository

class ImageRepositoryImpl(
    private val imageDao: ImageDao
): ImageRepository {
    override suspend fun insertImages(images: List<BreedImage>): List<Long> {
        return imageDao.insertImages(images.toImageEntity())
    }


}