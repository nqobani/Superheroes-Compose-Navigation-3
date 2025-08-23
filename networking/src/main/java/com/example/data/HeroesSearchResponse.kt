package com.example.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HeroesSearchResponse(
    val response: String,
    val results: List<Result>,
    @field:Json(name = "results-for")
    val resultsFor: String
)