package com.ntn.findit.ui.screen.createchallenge.location

import android.location.Address
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState

class LocationCreateChallengeViewModel : ViewModel() {

    private val _searchBar = MutableLiveData<String>()
    val searchBar: LiveData<String> = _searchBar

    private val _nextEnable = MutableLiveData<Boolean>()
    val nextEnable: LiveData<Boolean> = _nextEnable

    private val _marker = MutableLiveData<LatLng>()
    val marker = _marker

    private val _requestedLocation = MutableLiveData<Boolean>()
    val requestedLocation = _requestedLocation

    private val _cameraPosition = MutableLiveData<CameraPositionState>()
    val cameraPosition = _cameraPosition

    fun onLocationBarChange(str:String){
        _searchBar.value = str
    }

    fun onMarkerChange(latLng: LatLng) {
        _marker.value = latLng
        _cameraPosition.value = CameraPositionState(CameraPosition(latLng, 10f, 0.0F, 0.0F))
        _nextEnable.value = true
    }

    fun onRequestedLocation() {
        _requestedLocation.value = true
    }

    fun onLocationRequestFullFilled(latLng: LatLng) {
        onMarkerChange(latLng)
        _requestedLocation.value = false
    }

    fun onLocationSearchRequest(address: Address){
        onMarkerChange(LatLng(address.latitude, address.longitude))
        _searchBar.value = ""
    }


}