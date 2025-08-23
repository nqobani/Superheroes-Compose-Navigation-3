package com.example.data.di

import com.example.data.api.HeroesApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS).build()

    @Provides
    @Singleton
    fun providesRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://www.superheroapi.com/api.php/7152875b0a470403c458266367711e1f/") //TODO: Put the API key in the properties file
        .client(client).addConverterFactory(MoshiConverterFactory.create()).build()

    @Provides
    @Singleton
    fun providesHeroesApiService(retrofit: Retrofit): HeroesApiService = retrofit.create(
        HeroesApiService::class.java
    )
}