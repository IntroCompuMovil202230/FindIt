package com.ntn.findit.logic.location

import android.content.Context
import android.location.Address
import android.location.Geocoder

class GeoCoderService(context: Context) {
    private val geocoder = Geocoder(context)
    fun getFromLocation(place:String): Address? {
        val t = geocoder.getFromLocationName(place, 1)
        t?.let {
            if(t.size == 0){
                return null
            }
            return t[0]
        }
        return null
    }
}