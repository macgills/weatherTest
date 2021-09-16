package com.example.weathertest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weathertest.ui.WeatherScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = viewModel<WeatherViewModel>()
            val onTryAgainClicked = { viewModel.sendAction(WeatherAction.TryAgainClicked) }
            val onSwipeToRefresh = { viewModel.sendAction(WeatherAction.OnSwipeToRefresh) }
            WeatherScreen(
                viewModel.state.collectAsState().value,
                onTryAgainClicked,
                onSwipeToRefresh,
            )
        }
    }
}

