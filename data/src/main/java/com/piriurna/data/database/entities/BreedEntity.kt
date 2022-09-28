package com.piriurna.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "breed")
data class BreedEntity(
    @PrimaryKey
    val breedId: Int,
    val name : String,
    val category : String, // replaced with description
    val origin : String,
    val temperament : String,
    val imageUrl: String
) {
}