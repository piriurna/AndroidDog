package com.piriurna.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image")
data class ImageEntity(
    @PrimaryKey
    val imageId: String,
    val width : Int = 300,
    val height : Int = (width.toFloat()/DEFAULT_HEIGHT_RATIO).toInt(),
    val url : String
) {

    companion object {
        const val DEFAULT_HEIGHT_RATIO = 0.5625f
    }
}