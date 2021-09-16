package com.example.weathertest.data.model


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Coord(
    val lat: Double,
    val lon: Double
)
