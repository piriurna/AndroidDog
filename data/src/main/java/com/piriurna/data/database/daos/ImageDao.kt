package com.piriurna.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.piriurna.data.database.entities.BreedEntity
import com.piriurna.data.database.entities.ImageEntity
import com.piriurna.data.database.models.BreedWithImage

@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImages(images: List<ImageEntity>) : List<Long>

    @Query("SELECT * FROM image WHERE imageId = :id")
    suspend fun getImage(id : String) : ImageEntity
}