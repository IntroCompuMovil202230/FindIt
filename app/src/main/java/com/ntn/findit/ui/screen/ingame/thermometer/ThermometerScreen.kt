package com.ntn.findit.ui.screen.ingame.thermometer

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ntn.findit.generalviewmodels.CompassViewModel

@Composable
fun ThermometerScreen() {
    Thermometer()
}

@Composable
fun Thermometer(_viewModel: GameViewModel = viewModel(), _compassViewModel: CompassViewModel = viewModel()) {
    val temperature: Double by _viewModel.temperature.observeAsState(0.0)
    val degrees: Int by _compassViewModel.requestCompassSensorUpdates().observeAsState(0)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        OutlinedButton(
            onClick = { /*TODO*/ },
            border = BorderStroke(1.dp, Color.Black),
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 30.dp, top = 60.dp)
        ) {
            Text(text = "12:00")
        }
        OutlinedButton(
            onClick = { _viewModel.onStateChange(GameState.GameMapScreen) },
            border = BorderStroke(1.dp, Color.Blue),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 30.dp, top = 60.dp)
        ) {
            Text(text = "Mapa")
        }
        Text(
            text = temperature.toString(),
            fontSize = 100.sp,
            modifier = Modifier.align(Alignment.Center)
        )

        Text(
            text = degrees.toString(),
            fontSize = 30.sp,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 190.dp)
        )
        OutlinedButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 60.dp)
        ) {
            Text(text = "Ver pistas")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun Preview() {
    ThermometerScreen()
}