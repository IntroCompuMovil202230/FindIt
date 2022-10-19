package com.ntn.findit.generalviewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ntn.findit.logic.sensors.LightSensorLiveData


class LightSensorViewModel(application: Application) : AndroidViewModel(application) {

    private val lightData = LightSensorLiveData(application)
    fun requestLightSensorUpdates() = lightData
}