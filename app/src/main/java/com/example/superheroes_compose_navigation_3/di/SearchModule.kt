package com.example.superheroes_compose_navigation_3.di

import com.example.data.get_hero.HeroRepository
import com.example.data.search.SearchHeroesRepository
import com.example.superheroes_compose_navigation_3.helpers.DispatcherProvider
import com.example.superheroes_compose_navigation_3.features.Search.SearchUseCase
import com.example.superheroes_compose_navigation_3.features.details.GetHeroUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object HeroesModule {
    @Provides
    fun provideSearchUseCase(
        searchHeroesRepository: SearchHeroesRepository,
        dispatcherProvider: DispatcherProvider
    ): SearchUseCase {
        return SearchUseCase(searchHeroesRepository, dispatcherProvider)
    }

    @Provides
    fun provideGetHeroUseCase(
        heroRepository: HeroRepository,
        dispatcherProvider: DispatcherProvider
    ): GetHeroUseCase {
        return GetHeroUseCase(heroRepository, dispatcherProvider)
    }


}