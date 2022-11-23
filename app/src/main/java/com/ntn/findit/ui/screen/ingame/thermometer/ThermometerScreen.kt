package com.ntn.findit.ui.screen.ingame.thermometer

import android.util.Log
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ntn.findit.R
import com.ntn.findit.generalviewmodels.CompassViewModel

@Composable
fun ThermometerScreen() {
    Thermometer()
}

@Composable
fun Thermometer(_viewModel: GameViewModel = viewModel(), _compassViewModel: CompassViewModel = viewModel()) {
    val color by _viewModel.thermo.collectAsState()
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
            text = color.first,
            color = Color(color.second),
            fontSize = 60.sp,
            modifier = Modifier.align(Alignment.Center)
        )

        val currentRotation: Float by _compassViewModel.requestCompassSensorUpdates().observeAsState(0f)
        val (lastRotation, setLastRotation) = remember { mutableStateOf(0f) }
        var newRotation = lastRotation
        val modLast = if (lastRotation > 0) lastRotation % 360 else 360 - (-lastRotation % 360)

        if (modLast != currentRotation) {
            val backward =
                if (currentRotation > modLast) modLast + 360 - currentRotation else modLast - currentRotation
            val forward =
                if (currentRotation > modLast) currentRotation - modLast else 360 - modLast + currentRotation
            newRotation = if (backward < forward) {
                lastRotation - backward
            } else {
                lastRotation + forward
            }
            setLastRotation(newRotation)
        }

        val rotation: Float by animateFloatAsState(
            targetValue = -newRotation,
            animationSpec = tween(
                durationMillis = 150,
                easing = LinearEasing
            )
        )

        Image(
            modifier = Modifier
                .rotate(rotation)
                .size(265.dp)
                .padding(bottom = 15.dp)
                .align(Alignment.BottomCenter),
            painter = painterResource(id = R.drawable.wind_rose),
            colorFilter = ColorFilter.tint(MaterialTheme.colors.primary),
            contentDescription = ""
        )
    }
}





@Preview(showSystemUi = true)
@Composable
fun Preview() {
    ThermometerScreen()
}