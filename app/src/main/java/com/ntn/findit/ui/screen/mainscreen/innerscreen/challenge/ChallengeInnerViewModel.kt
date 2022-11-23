package com.ntn.findit.ui.screen.mainscreen.innerscreen.challenge

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.storage.FirebaseStorage
import com.ntn.findit.model.Challenge
import com.ntn.findit.model.Clue
import com.parse.ParseObject
import com.parse.ParseQuery
import com.parse.ktx.findAll
import com.parse.livequery.ParseLiveQueryClient
import com.parse.livequery.SubscriptionHandling
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.File
import kotlin.math.pow

class ChallengeInnerViewModel : ViewModel() {
    private val _challenges = MutableStateFlow<MutableList<Challenge>>(mutableStateListOf())
    val challenges = _challenges.asStateFlow()

    private val _weather = MutableStateFlow(Triple("", "", ""))
    val weather = _weather.asStateFlow()

    init {
        getChallenges()
    }

    fun requestIP(context: Context) {
        val queue = Volley.newRequestQueue(context)
        val jsonRequest = StringRequest("https://api.ipify.org", {
            requestLatLon(it, context)
        }, {
            Log.d("Mio", "Errorrrr")
        })
        queue.add(jsonRequest)
        queue.start()
    }

    private fun requestLatLon(ip: String, context: Context) {
        val queue = Volley.newRequestQueue(context)
        val jsonRequest =
            JsonObjectRequest(
                Request.Method.GET,
                "http://ip-api.com/json/$ip?fields=lat,lon",
                null,
                {
                    val lat = it.getString("lat")
                    val lon = it.getString("lon")
                    requestWeather(lat, lon, context)
                },
                {
                    Log.d("Mio", "Error latlon api $it")
                }
            )
        queue.add(jsonRequest)
        queue.start()
    }

    private fun requestWeather(lat: String, lon: String, context: Context) {
        val queue = Volley.newRequestQueue(context)
        val jsonRequest = JsonObjectRequest(
            Request.Method.GET,
            "https://api.open-meteo.com/v1/forecast?latitude=$lat&longitude=$lon&hourly=temperature_2m,relativehumidity_2m,rain",
            null,
            {
                //temperature_2m, relativehumidity_2m, rain
                val gen = it.getJSONObject("hourly")
                val temp = gen.getJSONArray("temperature_2m").get(12).toString()
                val humidity = gen.getJSONArray("relativehumidity_2m").get(12).toString()
                val rain = gen.getJSONArray("rain").get(12).toString()
                _weather.value = Triple(temp, rain, humidity)
                Log.d("Mio", "Temp: $temp, humidity: $humidity, rain: $rain")
            },
            {
                Log.d("Mio", "Error on weather api $it")
            })
        queue.add(jsonRequest)
        queue.start()
    }

    private fun getChallenges() {
        val parseLiveQueryClient: ParseLiveQueryClient = ParseLiveQueryClient.Factory.getClient()
        val parseQuery = ParseQuery<ParseObject>("Challenge")
        val subscriptionHandling: SubscriptionHandling<ParseObject> =
            parseLiveQueryClient.subscribe(parseQuery)

        subscriptionHandling.handleSubscribe { query ->
            run {
                query.findAll()
                query.findInBackground().onSuccess {
                    it.result.forEach {
                        val author = it.getString("author")
                        val name = it.getString("name")
                        val latitude = it.getDouble("latitude")
                        val longitude = it.getDouble("longitude")
                        val imgUrl = it.getString("imageUrl")

                        if(author != null && name != null && imgUrl != null){
                            val storageRef = FirebaseStorage.getInstance().reference
                            val uploadTask = storageRef.child(imgUrl).getBytes(
                                1024.0.pow(50.0).toLong()
                            )
                            uploadTask.addOnSuccessListener {

                                _challenges.value.add(
                                    Challenge(
                                        name, "", imgUrl, latitude, longitude, author, 0.0, BitmapFactory.decodeByteArray(it, 0, it.size)
                                    )
                                )
                                Log.d("Mio", _challenges.value[0].bitmap.toString())

                            }.addOnFailureListener {
                                Log.e("Frebase", "Image Retrive fail")
                            }



                        }

                    }

                }

            }
        }
    }
    private fun test(img: String){


    }
}