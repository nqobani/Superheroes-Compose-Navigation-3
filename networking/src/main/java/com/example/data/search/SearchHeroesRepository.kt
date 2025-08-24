package com.example.data.search

import com.example.data.models.heroes.HeroesSearchResponse

interface SearchHeroesRepository {
    suspend fun searchHeroes(query: String): Result<HeroesSearchResponse>
}

