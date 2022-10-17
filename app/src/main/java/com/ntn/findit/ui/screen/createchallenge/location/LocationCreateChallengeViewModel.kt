package com.ntn.findit.ui.screen.createchallenge.location

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng

class LocationCreateChallengeViewModel: ViewModel() {

    private val _searchBar = MutableLiveData<String>()
    val searchBar: LiveData<String> = _searchBar

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _nextEnable = MutableLiveData<Boolean>()
    val nextEnable: LiveData<Boolean> = _nextEnable

    private val _marker = MutableLiveData<LatLng>()
    val marker = _marker

    fun onMarkerChange(latLng: LatLng) {
        Log.d("Mio", "Hereeeeee")
        _marker.value = latLng
        _nextEnable.value = true
    }




}