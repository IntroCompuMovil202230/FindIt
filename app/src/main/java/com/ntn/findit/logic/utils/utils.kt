package com.ntn.findit.logic.utils

import android.util.Log
import com.google.android.gms.maps.model.LatLng
import java.math.RoundingMode
import kotlin.math.*

const val RADIUS_OF_EARTH_KM = 6371L
const val DISTANCE_REPORT = 15 // 15 metros de safe zone
const val MAX_DISTANCE = 215
val TEST = LatLng(4.628437, -74.064385) //Solo para pruebas TODO REMOVER


fun calculateDistance(lat1: Double, long1: Double, lat2: Double, long2: Double): Double {
    val latDistance = Math.toRadians(lat1 - lat2)
    val lngDistance = Math.toRadians(long1 - long2)
    val a = (sin(latDistance / 2) * sin(latDistance / 2)
            + (cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2))
            * sin(lngDistance / 2) * sin(lngDistance / 2)))
    val c = 2 * atan2(sqrt(a), sqrt(1 - a))
    val result: Double = RADIUS_OF_EARTH_KM * c
    return result *1000
}

