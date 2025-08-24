package com.example.data.di

import com.example.data.api.HeroesApiService
import com.example.data.search.SearchHeroesRepository
import com.example.data.search.SearchHeroesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchHeroesModule {
    @Provides
    @Singleton
    fun providesSearchHeroesRepository(heroesApiService: HeroesApiService): SearchHeroesRepository {
        return SearchHeroesRepositoryImpl(heroesApiService)
    }
}