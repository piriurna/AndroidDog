package com.piriurna.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.piriurna.data.database.daos.BreedDao
import com.piriurna.data.database.daos.ImageDao
import com.piriurna.data.database.entities.BreedEntity
import com.piriurna.data.database.entities.ImageEntity

@Database(
    entities = [
        BreedEntity::class,
        ImageEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AndroidDogDatabase: RoomDatabase() {

    abstract fun breedDao(): BreedDao

    abstract fun imageDao() : ImageDao
}
