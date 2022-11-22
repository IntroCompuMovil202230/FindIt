package com.ntn.findit.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Clue( var name: String, var type:String) : Parcelable {
}