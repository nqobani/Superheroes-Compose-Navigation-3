package com.example.data.api

import com.example.data.models.heroes.HeroResponse
import com.example.data.models.heroes.HeroesSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HeroesApiService {
    //https://www.superheroapi.com/api.php/7152875b0a470403c458266367711e1f/search/bat

    @GET("search/{query}")
    suspend fun searchHeroes(@Path("query") name: String): Response<HeroesSearchResponse>

    @GET("{id}")// There is no need for this. If this was a real life project I would just use the data from searchHeroes
    suspend fun getHero(@Path("id") id: String): Response<HeroResponse>
}