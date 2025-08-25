package com.example.data.models.heroes

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Work(
    val base: String,
    val occupation: String
)