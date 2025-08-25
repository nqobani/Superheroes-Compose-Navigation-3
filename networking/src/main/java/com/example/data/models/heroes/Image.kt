package com.example.data.models.heroes

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Image(
    val url: String
)