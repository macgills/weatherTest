package com.example.weathertest.ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.example.weathertest.ui.model.DayWithWeatherSummaries
import com.example.weathertest.ui.model.WeatherState
import com.example.weathertest.ui.theme.WeatherTestTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import java.time.DayOfWeek

@Preview(showBackground = true)
@Composable
fun WeatherScreen(
    @PreviewParameter(WeatherParams::class) state: WeatherState,
    onTryAgainClicked: () -> Unit = {},
    onSwipeToRefresh: () -> Unit = {},
) {
    WeatherTestTheme {
        Crossfade(targetState = state) {
            when (it) {
                WeatherState.Loading -> Loading()
                is WeatherState.Content -> Content(it, onSwipeToRefresh)
                is WeatherState.Error -> ErrorView(it.message, onTryAgainClicked)
            }
        }
    }
}

@Composable
private fun Content(state: WeatherState.Content, onSwipeToRefresh: () -> Unit) {
    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = state.isRefreshing),
        onRefresh = onSwipeToRefresh
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            state.daysWithWeatherSummaries.forEach {
                item(it.dayOfWeek) {
                    Text(
                        text = "${it.dayOfWeek}",
                        style = MaterialTheme.typography.h5
                    )
                }
                items(it.weatherSummaries) { summary ->
                    Text(text = summary)
                }
            }
        }
    }
}

@Composable
private fun Loading() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorView(message: String, onTryAgainClicked: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = message)
        Spacer(modifier = Modifier.size(8.dp))
        Button(onClick = onTryAgainClicked) {
            Text(text = "Try Again")
        }
    }
}

class WeatherParams : PreviewParameterProvider<WeatherState> {
    override val values: Sequence<WeatherState>
        get() {
            val content = WeatherState.Content(
                listOf(DayWithWeatherSummaries(DayOfWeek.MONDAY, listOf("lorem", "ipsum")))
            )
            return sequenceOf(
                WeatherState.Loading,
                WeatherState.Error("test"),
                content,
                content.copy(isRefreshing = true)
            )
        }
}
