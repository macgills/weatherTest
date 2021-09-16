package com.example.weathertest

import com.example.weathertest.data.WeatherService
import com.example.weathertest.data.WeatherService.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object FakeDi {
    val weatherService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(WeatherService::class.java)
}
