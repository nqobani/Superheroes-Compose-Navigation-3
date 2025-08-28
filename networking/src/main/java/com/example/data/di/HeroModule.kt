package com.example.data.di

import com.example.data.api.HeroesApiService
import com.example.data.get_hero.HeroRepository
import com.example.data.get_hero.HeroRepositoryImpl
import com.example.data.search.SearchHeroesRepository
import com.example.data.search.SearchHeroesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HeroModule {
    @Provides
    @Singleton
    fun providesHeroRepository(heroesApiService: HeroesApiService): HeroRepository {
        return HeroRepositoryImpl(heroesApiService)
    }
}