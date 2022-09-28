package com.piriurna.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image")
data class ImageEntity(
    @PrimaryKey
    val imageId: String,
    val height : Int,
    val width : Int,
    val url : String
) {
}