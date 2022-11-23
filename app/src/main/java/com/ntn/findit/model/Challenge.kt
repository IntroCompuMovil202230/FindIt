package com.ntn.findit.model
import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Challenge(
    var name: String, var description: String, var imageUrl:String,
    var latitude: Double, var longitude: Double,
    var creator:String, var rating:Double, var bitmap: Bitmap?
) : Parcelable {
    constructor() : this("","","",0.0,0.0,"",0.0, null)
}