package com.example.data.get_hero

import com.example.data.models.heroes.HeroResponse

interface HeroRepository {
    suspend fun getHero(id: String): Result<HeroResponse>
}

