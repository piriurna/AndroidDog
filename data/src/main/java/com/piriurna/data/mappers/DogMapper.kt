package com.piriurna.data.mappers

import com.piriurna.data.database.entities.BreedEntity
import com.piriurna.data.database.entities.ImageEntity
import com.piriurna.data.database.models.BreedWithImage
import com.piriurna.data.remote.dto.BreedDto
import com.piriurna.data.remote.dto.ImageDto
import com.piriurna.domain.models.Breed
import com.piriurna.domain.models.BreedImage

fun BreedDto.toBreed() : Breed {
    return Breed(
        id = this.id,
        name = this.name?:"",
        category = this.breedGroup?:"",
        origin = this.origin?:"",
        temperament = this.temperament?:"",
        image = this.imageDto.toImage(),
    )
}


fun List<BreedDto>.toBreed() : List<Breed> {
    return this.map { breedDto ->
        breedDto.toBreed()
    }
}

fun ImageDto.toImage() : BreedImage {
    return BreedImage(
        id = this.id,
        width = this.width,
        height = height,
        url = this.url
    )
}

fun ImageEntity.toImage() : BreedImage {
    return BreedImage(
        id = this.imageId,
        width = this.width,
        height = this.height,
        url = this.url
    )
}

fun BreedImage.toImageEntity() : ImageEntity {
    return ImageEntity(
        imageId = this.id,
        width = this.width,
        height = this.height,
        url = this.url
    )
}

fun List<BreedImage>.toImageEntity() : List<ImageEntity> {
    return this.map { breedImage ->
        breedImage.toImageEntity()
    }
}



fun Breed.toBreedEntity() : BreedEntity {
    return BreedEntity(
        breedId = this.id,
        name = this.name,
        category = this.category,
        origin = this.origin,
        temperament = this.temperament,
        breedImageId = this.image.id,
    )
}

fun List<Breed>.toBreedEntity() : List<BreedEntity> {
    return this.map { breed ->
        breed.toBreedEntity()
    }
}


fun BreedWithImage.toBreed() : Breed {
    return Breed(
        id = this.breed.breedId,
        name = this.breed.name?:"",
        category = this.breed.category?:"",
        origin = this.breed.origin?:"",
        temperament = this.breed.temperament?:"",
        image = this.image.toImage(),
    )
}

fun List<BreedWithImage>.toBreedObject() : List<Breed> {
    return this.map { breed ->
        breed.toBreed()
    }
}