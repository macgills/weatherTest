package com.example.weathertest.data.model


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Main(
    val feels_like: Double,
    val grnd_level: Int,
    val humidity: Int,
    val pressure: Int,
    val sea_level: Int,
    val temp: Double,
    val temp_kf: Double,
    val temp_max: Double,
    val temp_min: Double
)
