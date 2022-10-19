package com.ntn.findit.ui.screen.ingame.thermometer

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.ntn.findit.logic.utils.TEST //TODO CAMBIAR
import com.ntn.findit.logic.utils.calculateTemperature

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


    private fun onMarkerChange(latLng: LatLng) {
        _marker.value = latLng
    }
    private fun onTemperatureChange(){
        _temperature.value = _marker.value?.let { calculateTemperature(it, TEST) }
    }

    fun onStateChange(state: GameState) {
        _uiState.value = state
    }

    fun onLocationRequestFullFilled(latLng: LatLng) {
        onMarkerChange(latLng)
        onTemperatureChange()
        Log.d("Mio", "Temop: ${_temperature.value}")

    }


}