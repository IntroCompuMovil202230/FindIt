package com.ntn.findit.generalviewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ntn.findit.logic.sensors.CompassSensorLiveData

class CompassViewModel(application: Application): AndroidViewModel (application){
    private val lightData = CompassSensorLiveData(application)
    fun requestCompassSensorUpdates() = lightData
}