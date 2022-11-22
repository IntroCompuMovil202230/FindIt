package com.ntn.findit.model


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserNotification(val username: String, val latitude: Double, val longitude: Double):
    Parcelable