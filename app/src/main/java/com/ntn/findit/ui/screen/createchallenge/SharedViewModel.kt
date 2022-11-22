package com.ntn.findit.ui.screen.createchallenge

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ntn.findit.model.Challenge

class SharedViewModel : ViewModel() {
    // not used
    var challenge by mutableStateOf<Challenge?>(Challenge())
    private set

    fun setChallen(challeng: Challenge){
        challenge = challeng
    }

    fun setName(name:String){
        challenge?.name =name
    }

    fun setDescription(description:String){
        challenge?.description =description
    }

    fun setImageUrl(imageUrl:String){
        challenge?.imageUrl = imageUrl
    }



    fun setLatitude(latitude:Double){
        challenge?.latitude = latitude
    }

    fun setLongitude(longitude:Double){
        challenge?.longitude = longitude
    }

    fun setCreator(creator:String){
        challenge?.creator = creator
    }

    fun setRating(rating:Double){
        challenge?.rating = rating
    }
}
