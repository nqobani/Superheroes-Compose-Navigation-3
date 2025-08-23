package com.example.data.models.heroes

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Connections(
    @field:Json(name = "group-affiliation")
    val groupAffiliation: String,
    val relatives: String
)