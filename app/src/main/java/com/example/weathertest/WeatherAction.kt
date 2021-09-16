package com.example.weathertest

sealed class WeatherAction {
    object TryAgainClicked : WeatherAction()
    object OnSwipeToRefresh : WeatherAction()
}
