package com.example.data.get_hero

import com.example.data.api.HeroesApiService
import com.example.data.models.heroes.HeroResponse
import com.example.data.models.heroes.HeroesSearchResponse
import javax.inject.Inject

class HeroRepositoryImpl @Inject constructor(private val heroesApiService: HeroesApiService) :
    HeroRepository {
    override suspend fun getHero(id: String): Result<HeroResponse> {
        val response =  heroesApiService.getHero(id)
        return if (response.isSuccessful && response.body() != null) {
            Result.success(response.body()!!)
        } else {
            Result.failure(Exception(response.message())) // TODO: Create custom exceptions and handle them properly
        }
    }
}