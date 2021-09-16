package com.example.weathertest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathertest.ui.model.WeatherState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class WeatherViewModel : ViewModel() {

    private val _state: MutableStateFlow<WeatherState> = MutableStateFlow(WeatherState.Loading)
    val state: StateFlow<WeatherState> = _state.asStateFlow()

    private val weatherService = FakeDi.weatherService

    init {
        fetchWeather()
    }

    fun sendAction(action: WeatherAction) {
        handle(action, _state.value)
    }

    private fun fetchWeather() {
        viewModelScope.launch {
            kotlin.runCatching {
                weatherService.getWeather()
            }.fold(
                onSuccess = { emitState(WeatherState.Content(it)) },
                onFailure = { emitState(WeatherState.Error(it.message.orEmpty())) }
            )
        }
    }

    private fun emitState(value: WeatherState) {
        _state.tryEmit(value)
    }

    private fun handle(action: WeatherAction, weatherState: WeatherState) =
        when (action) {
            WeatherAction.TryAgainClicked -> onTryAgainClicked(weatherState)
            WeatherAction.OnSwipeToRefresh -> onSwipeToRefresh(weatherState)
        }

    private fun onSwipeToRefresh(weatherState: WeatherState) = when (weatherState) {
        is WeatherState.Content -> {
            emitState(weatherState.copy(isRefreshing = true))
            fetchWeather()
        }
        else -> {
            //do nothing
        }
    }

    private fun onTryAgainClicked(weatherState: WeatherState) = when (weatherState) {
        is WeatherState.Error -> {
            emitState(WeatherState.Loading)
            fetchWeather()
        }
        else -> {
            //do nothing
        }
    }
}

