@file:OptIn(ExperimentalPermissionsApi::class)

package com.ntn.findit.ui.screen.ingame.thermometer

import android.Manifest
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.ntn.findit.generalviewmodels.LocalizationViewModel
import com.ntn.findit.ui.screen.createchallenge.location.LocationCreateChallengeViewModel

@Composable
fun GameMapScreen() {
    GameMap()
}

@ExperimentalPermissionsApi
@Composable
fun GameMap(_viewModel: GameViewModel = viewModel()) {
    val markerState by _viewModel.marker.observeAsState()
    val uiSettings by remember { mutableStateOf(MapUiSettings(mapToolbarEnabled = false)) }
    val properties by remember {
        mutableStateOf(MapProperties(mapType = MapType.NORMAL))
    }
    val cameraPositionState = rememberCameraPositionState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            uiSettings = uiSettings,
            properties = properties,
            cameraPositionState = cameraPositionState
        ) {
            markerState?.let { MarkerState(it) }?.let {
                Marker(
                    state = it,
                    title = "Tu ubicación",
                    snippet = "Sigue buscando"
                )
            }

        }

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
            onClick = { _viewModel.onStateChange(GameState.Thermometer) },
            border = BorderStroke(1.dp, Color.Blue),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 30.dp, top = 60.dp)
        ) {
            Text(text = "Termómetro")
        }

        OutlinedButton(
            onClick = { _viewModel.onStateChange(GameState.SeeClues) },
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