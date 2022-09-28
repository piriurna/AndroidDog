package com.piriurna.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.piriurna.data.database.daos.BreedDao
import com.piriurna.data.database.entities.BreedEntity

@Database(
    entities = [
        BreedEntity::class
   ],
    version = 1,
    exportSchema = false
)
abstract class AndroidDogDatabase: RoomDatabase() {

    abstract fun breedDao(): BreedDao

}
