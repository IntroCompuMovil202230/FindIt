package com.ntn.findit.generalviewmodels

import android.app.Application
import android.location.Address
import androidx.lifecycle.AndroidViewModel
import com.ntn.findit.logic.location.GeoCoderService

class GeoCoderViewModel(application: Application) : AndroidViewModel(application)  {
    private val geocoderData = GeoCoderService(application)
    fun getFromLocation(place:String): Address? {
        return geocoderData.getFromLocation(place)
    }
}