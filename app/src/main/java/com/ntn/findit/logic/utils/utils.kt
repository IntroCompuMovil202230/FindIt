package com.ntn.findit.logic.utils

import com.google.android.gms.maps.model.LatLng
import kotlin.math.*

const val RADIUS_OF_EARTH_KM = 6371L
const val DISTANCE_REPORT = 15e-3 // 15 metros de safe zone
const val MAX_DISTANCE = 0.215
val TEST = LatLng(4.628437, -74.064385) //Solo para pruebas TODO REMOVER

fun calculateTemperature(pos: LatLng, reference: LatLng): Double {
    val dis =
        calculateDistance(pos.latitude,
            pos.longitude,
            reference.latitude,
            reference.longitude)/1000
    if (dis > MAX_DISTANCE)
        return 0.0
    return (dis-15)/2

}

fun calculateDistance(lat1: Double, long1: Double, lat2: Double, long2: Double): Double {
    val latDistance = Math.toRadians(lat1 - lat2)
    val lngDistance = Math.toRadians(long1 - long2)
    val a = (sin(latDistance / 2) * sin(latDistance / 2)
            + (cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2))
            * sin(lngDistance / 2) * sin(lngDistance / 2)))
    val c = 2 * atan2(sqrt(a), sqrt(1 - a))
    val result: Double = RADIUS_OF_EARTH_KM * c
    return (result * 100.0).roundToInt() / 100.0
}

