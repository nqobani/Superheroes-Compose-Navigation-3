package com.example.data.search

import com.example.data.api.HeroesApiService
import com.example.data.models.heroes.HeroesSearchResponse
import retrofit2.Response
import javax.inject.Inject

class SearchHeroesRepositoryImpl @Inject constructor(private val heroesApiService: HeroesApiService) :
    SearchHeroesRepository {
    override suspend fun searchHeroes(query: String): Result<HeroesSearchResponse> {
        val response =  heroesApiService.searchHeroes(query)
        return if (response.isSuccessful && response.body() != null) {
            Result.success(response.body()!!)
        } else {
            Result.failure(Exception(response.message())) // TODO: Create custom exceptions and handle them properly
        }
    }
}