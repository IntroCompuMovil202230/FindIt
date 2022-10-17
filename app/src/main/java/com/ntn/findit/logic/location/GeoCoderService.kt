package com.ntn.findit.logic.location

import android.content.Context
import android.location.Address
import android.location.Geocoder

class GeoCoderService(context: Context) {
    private val geocoder = Geocoder(context)
    fun getFromLocation(place:String): Address? {
        return geocoder.getFromLocationName(place, 1)?.get(0)
    }
}