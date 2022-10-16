package com.ntn.findit.ui.screen.ingame.thermometer

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.maps.android.compose.GoogleMap

@Composable
fun GameMapScreen() {
    GameMap()
}

@Composable
fun GameMap() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) { // TODO CAMBIAR COLOR
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
            onClick = { /*TODO*/ },
            border = BorderStroke(1.dp, Color.Blue),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 30.dp, top = 60.dp)
        ) {
            Text(text = "Termómetro")
        }
        GoogleMap(modifier = Modifier.fillMaxSize()) {

        }
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


@Composable
@Preview(showSystemUi = true)
fun PreviewMap() {
    GameMapScreen()
}