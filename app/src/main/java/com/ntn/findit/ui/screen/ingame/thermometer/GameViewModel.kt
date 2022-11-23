package com.ntn.findit.ui.screen.ingame.thermometer

import android.content.res.AssetManager
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.ntn.findit.logic.utils.TEST
import com.ntn.findit.logic.utils.calculateDistance
import com.ntn.findit.model.Challenge
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

sealed class GameState{
    object SeeClues: GameState()
    object Thermometer: GameState()
    object GameMapScreen: GameState()
    object CancelAlertDialog: GameState()
    object WinAlertDialog: GameState()
}


class GameViewModel: ViewModel() {
    private val _uiState = mutableStateOf<GameState>(GameState.Thermometer)
    val uiState: State<GameState>
        get() = _uiState

    private val _marker = MutableLiveData<LatLng>()
    val marker = _marker

    private val _challenge = MutableStateFlow(Challenge())
    val challenge = _challenge.asStateFlow()


    private val _distance = MutableLiveData<Double>()
    val distance = _distance

    private val _mapStyle = MutableLiveData<MapProperties>()
    val mapStyle = _mapStyle

    private val _thermo = MutableStateFlow(Pair("", 0x0L))
    val thermo = _thermo.asStateFlow()


    private fun onMarkerChange(latLng: LatLng) {
        _marker.value = latLng
    }

    fun onStateChange(state: GameState) {
        _uiState.value = state
    }

    fun onLocationRequestFullFilled(latLng: LatLng) {
        onMarkerChange(latLng)
        val distance = calculateDistance(latLng.latitude, latLng.longitude, challenge.value.latitude, challenge.value.longitude)
        Log.d("Mio", "distance : $distance")
        getTermomether(distance)
    }

    fun darkMode(assets: AssetManager) {
        viewModelScope.launch {
            val str: String
            assets.open("dark_mode.json").bufferedReader().use {
                str = it.readText()
            }
            _mapStyle.value = MapProperties(
                mapType = MapType.NORMAL,
                mapStyleOptions = MapStyleOptions(str)
            )
        }
        Log.d("Mio", "DARK MODEEEEEEE")
    }

    fun lightMode() {
        _mapStyle.value = MapProperties(
            mapType = MapType.NORMAL,
            mapStyleOptions = MapStyleOptions("[]")
        )
    }

    fun setChallenge(challenge: Challenge){
        _challenge.value = challenge
    }


    fun getTermomether(distance: Double){
        _thermo.value = if(distance >= 1000){
            Pair("Congelado", 0xFF003B64)
        }else if(distance >= 700){
            Pair("Extra frío", 0xFF0474BA)
        }else if(distance >= 500){
            Pair("Frío", 0xFF00A7E1)
        }else if(distance >= 300){
            Pair("Medio", 0xFFEBEBEB)
        }else if(distance >= 100){
            Pair("Caliente", 0xFFFFA630)
        }else if (distance >= 40){
            Pair("Muy caliente",0xFFF17720)
        }else{
            Pair("Hirviendo", 0xFFE0495F)
        }

    }

}