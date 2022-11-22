package com.ntn.findit.ui.screen.createchallenge.location

import android.location.Address
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.ntn.findit.model.Challenge
import com.parse.ParseObject
import com.parse.ParseQuery
import kotlinx.coroutines.launch

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

    fun saveBasicChallengeData(challenge: Challenge){
        viewModelScope.launch {
            val query = ParseQuery.getQuery<ParseObject>("Challenge")
            query.whereEqualTo("name", challenge.name)
            query.getFirstInBackground().onSuccess{  obj->
                obj.result.put("name", challenge.name)
                obj.result.put("description", challenge.description)
                obj.result.put("imageUrl", challenge.imageUrl)
                obj.result.put("author", challenge.creator)
                obj.result.put("latitude", challenge.latitude)
                obj.result.put("longitude", challenge.longitude)
                obj.result.put("rating", challenge.rating)
                obj.result.save()
                Log.i("push challenge","lo actualizo")
            }
            query.countInBackground().onSuccess{it->
                if(it.result == 0){
                    val ob = ParseObject("Challenge")
                    Log.i("push challenge", "si lo creo")
                    ob.put("name", challenge.name)
                    ob.put("description", challenge.description)
                    ob.put("imageUrl", challenge.imageUrl)
                    ob.put("author", challenge.creator)
                    ob.put("latitude", challenge.latitude)
                    ob.put("longitude", challenge.longitude)
                    ob.put("rating", challenge.rating)
                    ob.save()
                }
            }
        }
    }


}