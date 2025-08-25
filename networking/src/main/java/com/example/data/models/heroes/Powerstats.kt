package com.example.data.models.heroes

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Powerstats(
    val combat: String,
    val durability: String,
    val intelligence: String,
    val power: String,
    val speed: String,
    val strength: String
)