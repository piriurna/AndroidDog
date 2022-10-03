package com.piriurna.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "breed",
    foreignKeys = [
        ForeignKey(
            entity = ImageEntity::class,
            parentColumns = arrayOf("imageId"),
            childColumns = arrayOf("breedImageId"),
            onDelete = ForeignKey.CASCADE
        ),
    ])
data class BreedEntity(
    @PrimaryKey
    val breedId: Int,
    val name : String?,
    val category : String?, // replaced with description
    val origin : String?,
    val temperament : String?,
    @ColumnInfo(index = true)
    val breedImageId: String?
) {
}