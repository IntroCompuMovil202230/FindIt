package com.ntn.findit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ntn.findit.logic.location.LocationLiveData

class LocalizationViewModel(application: Application) : AndroidViewModel(application) {

    private val locationData = LocationLiveData(application)
    fun requestLocationUpdates() = locationData
    fun getSingleLocation() = locationData.getSingleLocation()
}