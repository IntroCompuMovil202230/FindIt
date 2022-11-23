@file:OptIn(ExperimentalPermissionsApi::class)

package com.ntn.findit.ui.screen.ingame.thermometer

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.*
import com.ntn.findit.generalviewmodels.LightSensorViewModel

@Composable
fun GameMapScreen() {
    GameMap()
}

@ExperimentalPermissionsApi
@Composable
fun GameMap(
    _viewModel: GameViewModel = viewModel(),
    _lightSensorVM: LightSensorViewModel = viewModel()
) {
    val markerState by _viewModel.marker.observeAsState()
    val uiSettings by remember { mutableStateOf(MapUiSettings(mapToolbarEnabled = false)) }
    val mapProperties: MapProperties by _viewModel.mapStyle.observeAsState(
        MapProperties(
            mapType = MapType.NORMAL,
            mapStyleOptions = MapStyleOptions("[]")
        )
    )
    val challenge by _viewModel.challenge.collectAsState()
    val lightSensor: Float by _lightSensorVM.requestLightSensorUpdates().observeAsState(20000f)
    val context = LocalContext.current

    if (lightSensor < 5000) {
        _viewModel.darkMode(context.assets)
    } else {
        _viewModel.lightMode()
    }


    var seePoint by remember {
        mutableStateOf(false)
    }
    val cameraPositionState = rememberCameraPositionState(){
        if(markerState != null)
            position = CameraPosition.fromLatLngZoom(markerState!!, 10f)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            uiSettings = uiSettings,
            properties = mapProperties,
            cameraPositionState = cameraPositionState,
        ) {
            markerState?.let { MarkerState(it) }?.let {
                Marker(
                    state = it,
                    title = "Tu ubicación",
                    snippet = "Sigue buscando"
                )
            }

            if(seePoint){
                Marker(
                    state = MarkerState(LatLng(challenge.latitude, challenge.longitude)),
                    title = challenge.name,
                    snippet = challenge.description,
                    icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)
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
            onClick = { seePoint = !seePoint },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 60.dp)
        ) {
            Text(text = "Ver punto")
        }
    }
}


@Composable
@Preview(showSystemUi = true)
fun PreviewMap() {
    GameMapScreen()
}