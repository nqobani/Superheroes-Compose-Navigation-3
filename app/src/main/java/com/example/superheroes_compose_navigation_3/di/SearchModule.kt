package com.example.superheroes_compose_navigation_3.di

import com.example.data.search.SearchHeroesRepository
import com.example.superheroes_compose_navigation_3.helpers.DispatcherProvider
import com.example.superheroes_compose_navigation_3.features.Search.SearchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object SearchModule {
    @Provides
    fun provideSearchUseCase(
        searchHeroesRepository: SearchHeroesRepository,
        dispatcherProvider: DispatcherProvider
    ): SearchUseCase {
        return SearchUseCase(searchHeroesRepository, dispatcherProvider)
    }


}