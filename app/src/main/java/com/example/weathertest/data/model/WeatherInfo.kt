package com.example.weathertest.data.model


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherInfo(
    val clouds: Clouds,
    val dt: Int,
    val dt_txt: String,
    val main: Main,
    val pop: Double,
    val rain: Rain? = null,
    val sys: Sys,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)
