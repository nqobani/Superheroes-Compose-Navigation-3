package com.example.data.models.heroes

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HeroResponse(
    val response: String,
    val appearance: Appearance?,
    val biography: Biography?,
    val connections: Connections?,
    val id: String?,
    val image: Image?,
    val name: String?,
    val powerstats: Powerstats?,
    val work: Work?
)