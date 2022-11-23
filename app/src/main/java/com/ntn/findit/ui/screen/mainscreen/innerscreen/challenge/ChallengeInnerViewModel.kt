package com.ntn.findit.ui.screen.mainscreen.innerscreen.challenge

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.ntn.findit.model.Challenge
import com.ntn.findit.model.Clue
import com.parse.ParseObject
import com.parse.ParseQuery
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ChallengeInnerViewModel : ViewModel() {
    private val _challenges = MutableStateFlow<MutableList<Challenge>>(mutableStateListOf())
    val challenges = _challenges.asStateFlow()
/*
    init{
        getClues()
        Log.i("esto saca","asdfasdfasdf")
    }
*/
    fun getClues(){
        val query = ParseQuery.getQuery<ParseObject>("Challenge")
        query.findInBackground().onSuccess { listResult ->
            listResult.result.forEach {

                /*
                   var name: String, var description: String, var imageUrl:String,
    var latitude: Double, var longitude: Double,
    var creator:String, var rating:Double)*/
                val name = it.getString("name")
                val desc = it.getString("description")
                val imageUrl = it.getString("imageUrl")
                val creator = it.getString("creator")
                val latitude = it.getDouble("latitude")
                val longitude = it.getDouble("longitude")
                val rating = it.getDouble("rating")

                if (name != null && desc!= null && imageUrl != null && creator != null){
                    Log.i("esto saca",name+desc)
                    val c = Challenge(
                        name = name,
                        description = desc,
                        imageUrl = imageUrl,
                        creator = creator,
                        latitude = latitude,
                        longitude = longitude,
                        rating = rating
                    )
                    if(!challenges.value.contains(c)){
                        challenges.value.add(
                            c
                        )
                    }
                }

            }
        }
        query.countInBackground().onSuccess{it->
            Log.i("resultado",it.result.toString())
        }
    }
}