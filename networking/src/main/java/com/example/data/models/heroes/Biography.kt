package com.example.data.models.heroes

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Biography(
    val aliases: List<String>,
    val alignment: String,
    @field:Json(name = "alter-egos")
    val alterEgos: String,
    @field:Json(name = "first-appearance")
    val firstAppearance: String,
    @field:Json(name = "full-name")
    val fullName: String,
    @field:Json(name = "place-of-birth")
    val placeOfBirth: String,
    val publisher: String
)