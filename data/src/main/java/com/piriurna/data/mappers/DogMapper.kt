package com.piriurna.data.mappers

import com.piriurna.data.remote.dto.BreedDto
import com.piriurna.domain.models.Breed

fun BreedDto.toBreed() : Breed {
    return Breed(
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