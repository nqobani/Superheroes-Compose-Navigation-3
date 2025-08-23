package com.example.data.models.heroes

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Appearance(
    @field:Json(name = "eye-color")
    val eyeColor: String,
    val gender: String,
    @field:Json(name = "hair-color")
    val hairColor: String,
    val height: List<String>,
    val race: String,
    val weight: List<String>
)