package com.example.weathertest.ui.model

import com.example.weathertest.data.model.WeatherInfo
import java.time.DayOfWeek
import java.time.LocalDate

data class DayWithWeatherSummaries(val dayOfWeek: DayOfWeek, val weatherSummaries: List<String>) {
    constructor(dateAndWeatherGrouping: Map.Entry<String, List<WeatherInfo>>) : this(
        LocalDate.parse(dateAndWeatherGrouping.key).dayOfWeek,
        dateAndWeatherGrouping.value.map { summary(it) }
    )

    companion object {
        private fun summary(value: WeatherInfo) =
            "${value.dt_txt.substringAfter(" ")} feels like ${value.main.feels_like}°C" +
                    " ${value.weather.joinToString { it.description }}"
    }

}
