@file:OptIn(ExperimentalPermissionsApi::class)

package com.ntn.findit.ui.screen.createchallenge.location

import android.Manifest
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.*
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.ntn.findit.generalviewmodels.GeoCoderViewModel
import com.ntn.findit.generalviewmodels.LocalizationViewModel
import com.ntn.findit.ui.screen.createchallenge.CreateChallenge
import com.ntn.findit.ui.screen.shared.CustomSpacer
import com.ntn.findit.ui.theme.LightWhite
import kotlinx.coroutines.launch

@Composable
fun LocationCreateChallengeScreen(
    navController: NavController,
    _viewModel: LocationCreateChallengeViewModel = viewModel(),
    _locationViewModel: LocalizationViewModel = viewModel()
) {
    Column(
        Modifier.padding(vertical = 45.dp, horizontal = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LinearProgressIndicator(progress = 0.4f, modifier = Modifier.fillMaxWidth())
        CustomSpacer(15.0)
        Title()
        CustomSpacer(15.0)
        SearchBar()
        CustomSpacer(5.0)
        MiniMap()
        Spacer(modifier = Modifier.weight(1f))
        Foot(navController)
    }
}


@Composable
fun Title() {
    Text(text = "Ubicación Objetivo", fontWeight = FontWeight.ExtraBold, fontSize = 30.sp)
    CustomSpacer(10.0)
    Text(text = "Elige o ingresa una ubicación para el objetivo")

}

@Composable
fun SearchBar(
    _viewModel: LocationCreateChallengeViewModel = viewModel(),
    _geocoderViewModel: GeoCoderViewModel = viewModel()
) {
    val searchBar: String by _viewModel.searchBar.observeAsState("")
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    TextField(
        value = searchBar,
        label = { Text("Busca un lugar") },
        onValueChange = { _viewModel.onLocationBarChange(it) },
        leadingIcon = { Icon(Icons.Default.Search, "") },
        shape = RoundedCornerShape(50),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = LightWhite,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions(imeAction = androidx.compose.ui.text.input.ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {
                coroutineScope.launch {
                    val t = _geocoderViewModel.getFromLocation(searchBar)
                    if (t != null) {
                        _viewModel.onLocationSearchRequest(t)
                    }else{
                        Toast.makeText(context, "Error on geocoder", Toast.LENGTH_LONG).show()
                    }
                }.runCatching {
                    if(this.isCancelled){
                        Toast.makeText(context, "Error on geocoder", Toast.LENGTH_LONG).show()
                    }

                }

            }
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun MiniMap(
    _viewModel: LocationCreateChallengeViewModel = viewModel()
) {
    val markerState by _viewModel.marker.observeAsState()
    val uiSettings by remember { mutableStateOf(MapUiSettings(mapToolbarEnabled = false)) }
    val properties by remember {
        mutableStateOf(MapProperties(mapType = MapType.NORMAL))
    }
    val cameraPositionState by _viewModel.cameraPosition.observeAsState(CameraPositionState())


    Box(
        modifier = Modifier
            .size(300.dp)
            .fillMaxWidth()
    ) {
        GoogleMap(
            googleMapOptionsFactory = {
                GoogleMapOptions().mapId("MyMapId")
            },
            uiSettings = uiSettings,
            properties = properties,
            modifier = Modifier.fillMaxWidth(),
            onMapLongClick = {
                _viewModel.onMarkerChange(it)
            },
            cameraPositionState = cameraPositionState
        )
        {
            markerState?.let { MarkerState(it) }?.let {
                Marker(
                    state = it,
                    title = "Tu ubicación",
                    snippet = "Donde quieres tu lugar de destino"
                )
            }
        }

    }
}

@ExperimentalPermissionsApi
@Composable
fun Foot(navController: NavController, _viewModel: LocationCreateChallengeViewModel = viewModel()) {
    val nextEnabled by _viewModel.nextEnable.observeAsState(false)
    val requestedLocation by _viewModel.requestedLocation.observeAsState(false)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        if (requestedLocation) {
            RequestLocationPermission()
        }
        OutlinedButton(onClick = {
            _viewModel.onRequestedLocation()
        }) {
            Icon(imageVector = Icons.Default.LocationOn, contentDescription = "")
            CustomSpacer(2.0)
            Text(text = "Seleccionar mi ubicación actual")
        }

        CustomSpacer(5.0)
        Row {
            OutlinedButton(onClick = {}) {
                Text(text = "Anterior")
            }
            CustomSpacer(30.0)
            Button(
                onClick = { navController.navigate(CreateChallenge.AddClue.route) },
                enabled = nextEnabled
            ) {
                Text(text = "Siguiente")
            }
        }
    }
}

@ExperimentalPermissionsApi
@Composable
private fun RequestLocationPermission(
    _viewModel: LocationCreateChallengeViewModel = viewModel(),
    _locationViewModel: LocalizationViewModel = viewModel()
) {
    val permissionState = rememberPermissionState(
        permission = Manifest.permission.ACCESS_FINE_LOCATION
    )
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(
        key1 = lifecycleOwner,
        effect = {
            val observer = LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_START) {
                    permissionState.launchPermissionRequest()
                }
            }
            lifecycleOwner.lifecycle.addObserver(observer)

            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
            }
        }
    )

    when {
        permissionState.status.isGranted -> {
            val location by _locationViewModel.requestLocationUpdates().observeAsState()
            if (location == null) {
                Log.d("Mio", "Location: null")
            } else {
                Log.d("Mio", "HOLAAAAAAA")
                location?.let { LatLng(it.latitude, it.longitude) }
                    ?.let { _viewModel.onLocationRequestFullFilled(it) }
                Log.d("Mio", "Message: ${location?.latitude}")
            }
        }
        permissionState.status.shouldShowRationale -> {
            Log.d("Mio", "Retry permission")
        }
        else -> {
            Log.d("Mio", "Denied")
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun Preview() {
    LocationCreateChallengeScreen(rememberNavController())
}
