package com.piriurna.data.mappers

import com.piriurna.data.database.entities.BreedEntity
import com.piriurna.data.remote.dto.BreedDto
import com.piriurna.domain.models.Breed

fun BreedDto.toBreed() : Breed {
    return Breed(
        id = this.id,
        name = this.name,
        category = this.description,
        origin = this.origin,
        temperament = this.temperament,
        imageUrl = this.imageDto.url,
    )
}


fun List<BreedDto>.toBreed() : List<Breed> {
    return this.map { breedDto ->
        breedDto.toBreed()
    }
}


fun Breed.toBreedEntity() : BreedEntity {
    return BreedEntity(
        breedId = this.id,
        name = this.name,
        category = this.category,
        origin = this.origin,
        temperament = this.temperament,
        imageUrl = this.imageUrl,
    )
}

fun List<Breed>.toBreedEntity() : List<BreedEntity> {
    return this.map { breed ->
        breed.toBreedEntity()
    }
}


fun BreedEntity.toBreed() : Breed {
    return Breed(
        id = this.breedId,
        name = this.name,
        category = this.category,
        origin = this.origin,
        temperament = this.temperament,
        imageUrl = this.imageUrl,
    )
}

fun List<BreedEntity>.toBreedObject() : List<Breed> {
    return this.map { breed ->
        breed.toBreed()
    }
}