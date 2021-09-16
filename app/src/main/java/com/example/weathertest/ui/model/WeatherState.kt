package com.example.weathertest.ui.model

import com.example.weathertest.data.model.WeatherResponse

sealed class WeatherState {
    object Loading : WeatherState()

    data class Content(
        val daysWithWeatherSummaries: List<DayWithWeatherSummaries>,
        val isRefreshing: Boolean = false
    ) : WeatherState() {

        constructor(weatherResponse: WeatherResponse) : this(
            weatherResponse.list.groupBy { it.dt_txt.substringBefore(" ") }
                .map(::DayWithWeatherSummaries)
        )
    }

    data class Error(val message: String) : WeatherState()
}
