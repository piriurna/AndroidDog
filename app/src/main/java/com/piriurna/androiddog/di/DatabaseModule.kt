package com.piriurna.androiddog.di

import android.content.Context
import androidx.room.Room
import com.piriurna.androiddog.DatabaseConstants.DATABASE_NAME
import com.piriurna.data.database.AndroidDogDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        AndroidDogDatabase::class.java,
        DATABASE_NAME
    ).fallbackToDestructiveMigration().build()
}

