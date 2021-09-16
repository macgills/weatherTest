package com.example.weathertest.data.model


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Sys(
    val pod: String
)
