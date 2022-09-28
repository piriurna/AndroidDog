package com.piriurna.androiddog.di

import com.piriurna.data.database.AndroidDogDatabase
import com.piriurna.data.remote.sources.DogApiSource
import com.piriurna.data.repository.DogRepositoryImpl
import com.piriurna.domain.repositories.DogRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {


    @Provides
    @Singleton
    fun provideDogRepository(dogApiSource: DogApiSource, androidDogDatabase: AndroidDogDatabase): DogRepository {
        return DogRepositoryImpl(dogApiSource,androidDogDatabase.breedDao())
    }


}