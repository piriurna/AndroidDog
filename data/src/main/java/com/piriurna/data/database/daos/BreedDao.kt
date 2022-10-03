package com.piriurna.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.piriurna.data.database.entities.BreedEntity
import com.piriurna.data.database.models.BreedWithImage

@Dao
interface BreedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBreedList(breeds: List<BreedEntity>) : List<Long>

    @Query("SELECT * FROM breed")
    suspend fun getBreedList() : List<BreedWithImage>

    @Query("SELECT * FROM breed WHERE name LIKE '%' || :queryString || '%'")
    suspend fun getBreedsWithQuery(queryString : String) : List<BreedWithImage>
}