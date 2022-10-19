package com.ntn.findit.ui.screen.ingame.thermometer

import android.content.res.AssetManager
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.ntn.findit.logic.utils.TEST
import com.ntn.findit.logic.utils.calculateTemperature
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


    private val _temperature = MutableLiveData<Double>()
    val temperature = _temperature

    private val _mapStyle = MutableLiveData<MapProperties>()
    val mapStyle = _mapStyle


    private fun onMarkerChange(latLng: LatLng) {
        _marker.value = latLng
    }
    private fun onTemperatureChange(){
        _temperature.value = _marker.value?.let { calculateTemperature(it, TEST) }
        if(_temperature.value == 100.0){
            _uiState.value = GameState.WinAlertDialog
        }

    }

    fun onStateChange(state: GameState) {
        _uiState.value = state
    }

    fun onLocationRequestFullFilled(latLng: LatLng) {
        onMarkerChange(latLng)
        onTemperatureChange()
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


}