package com.piriurna.data.database.models

import androidx.room.Embedded
import androidx.room.Relation
import com.piriurna.data.database.entities.BreedEntity
import com.piriurna.data.database.entities.ImageEntity

data class BreedWithImage(
    @Embedded val breed : BreedEntity,
    @Relation(
        parentColumn = "breedImageId",
        entityColumn = "imageId"
    )
    val image : ImageEntity,

) {}