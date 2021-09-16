package com.example.weathertest.data

import com.example.weathertest.data.model.WeatherResponse
import retrofit2.http.GET

interface WeatherService {
    @GET("/data/2.5/forecast?q=München,DE&appid=b2463975fc764a216077df55e7d20171&units=metric")
    suspend fun getWeather(): WeatherResponse

    companion object {
        const val BASE_URL = "https://api.openweathermap.org"
    }
}
