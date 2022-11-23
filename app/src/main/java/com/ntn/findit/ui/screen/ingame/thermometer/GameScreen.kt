@file:OptIn(ExperimentalPermissionsApi::class)

package com.ntn.findit.ui.screen.ingame.thermometer

import android.Manifest
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.google.android.gms.maps.model.LatLng
import com.ntn.findit.generalviewmodels.LocalizationViewModel
import com.ntn.findit.model.Challenge

@Composable
fun Game(navController: NavController, _viewModel : GameViewModel = viewModel()) {
    LaunchedEffect(key1 = Unit){
        val challenge = navController.previousBackStackEntry?.savedStateHandle?.get<Challenge>("challenge")
        if (challenge != null) {
            _viewModel.setChallenge(challenge)
        }

    }
    RequestLocationPermission()
    when(_viewModel.uiState.value){
        is GameState.Thermometer -> ThermometerScreen()
        is GameState.GameMapScreen -> GameMap()
        is GameState.CancelAlertDialog -> EndConfirmation()
        is GameState.SeeClues -> ShowCluesScreen()
        is GameState.WinAlertDialog -> WinConfirmation()
    }
}
@ExperimentalPermissionsApi
@Composable
private fun RequestLocationPermission(
    _viewModel: GameViewModel = viewModel(),
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

    val context = LocalContext.current
    when {
        permissionState.status.isGranted -> {
            val location by _locationViewModel.requestLocationUpdates().observeAsState()
            if (location == null) {
                Log.d("Mio", "Location: null")
            } else {
                location?.let { LatLng(it.latitude, it.longitude) }
                    ?.let { _viewModel.onLocationRequestFullFilled(it) }
            }
        }
        permissionState.status.shouldShowRationale -> {
            Log.d("Mio", "Retry permission")
        }
        !permissionState.status.isGranted -> {
            Toast.makeText(
                context,
                "Se necesitan permisos de localización para jugar",
                Toast.LENGTH_LONG
            ).show()
            Log.d("Mio", "Denied")
        }
    }
}

