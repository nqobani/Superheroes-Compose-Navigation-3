package com.example.superheroes_compose_navigation_3.di

import com.example.superheroes_compose_navigation_3.helpers.DefaultDispatcherProvider
import com.example.superheroes_compose_navigation_3.helpers.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDispatcherProvider(): DispatcherProvider {
        return DefaultDispatcherProvider()
    }
}