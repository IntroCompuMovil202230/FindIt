package com.ntn.findit.ui.screen.createchallenge.challengepreview

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ntn.findit.model.Challenge
import com.parse.ParseObject
import com.parse.ParseQuery
import com.parse.ParseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class CreateChallengePreviewViewModel: ViewModel() {
    private val _name = MutableStateFlow<String>("")
    val name = _name

    private val _description = MutableStateFlow<String>("")
    val description = _description

    private val _creator = MutableStateFlow<String>("")
    val creator = _creator

    private val _rating = MutableLiveData<Double>(0.0)
    val rating = _rating

    private val _numClues = MutableLiveData<String>("")
    val numClues = _numClues

    private val _image = MutableStateFlow<Uri?>(null)
    val image = _image.asStateFlow()

    fun setImage(image: Uri) {
        _image.value = image
    }

    fun setName(name: String){
        _name.value = name
    }

    fun setDescription(description : String){
        _description .value = description
    }

    fun setCreator(creator : String){
        _creator.value = creator
    }

    fun setNumClues(numClues : String){
        _numClues.value = numClues
    }

    fun setRating(rating : Double){
        _rating.value = rating
    }



    fun saveBasicChallengeData(challenge: String){
        viewModelScope.launch {
            val query = ParseQuery.getQuery<ParseObject>("Challenge")
            query.whereEqualTo("name", challenge)
            query.getFirstInBackground().onSuccess{  it->
                val challengeName = it.result.getString("name")
                val desc = it.result.getString("description")
                val challengeAuthor = it.result.getString("author")
                val imageUrl = it.result.getString("imageUrl")
                val crating = it.result.getDouble("rating")

                if (challengeAuthor != null) {
                    setCreator(challengeAuthor)
                }
                if (challengeName != null) {
                    setName(challengeName)
                }
                if (desc != null) {
                    setDescription(desc)
                }

                setRating(crating)

                val query2 = ParseQuery.getQuery<ParseObject>("Clue")
                query2.whereEqualTo("challengeName", challenge)
                query2.countInBackground().onSuccess{it->
                    Log.i("cuenta","cuenta"+it.result.toString())
                    setNumClues(it.result.toString())
                }

            }

        }
    }

    fun pushAvailableNotification() {

        val client = OkHttpClient()
        val type = "application/json; charset=utf-8".toMediaType()


        val json: String = mapToJson(
            "Nuevo reto disponible  \uD83D\uDDFA️\uD83C\uDDE8\uD83C\uDDF4",
            ParseUser.getCurrentUser().username + " ha creado un reto para jugar \uD83E\uDDED \uD83D\uDCCD \uD83D\uDCCD",
            ParseUser.getCurrentUser().username
        )


        val body = RequestBody.create(type, json)
        val request = Request.Builder()
            .url("http://3.80.151.200:1337/parse/push")
            .addHeader("X-Parse-Application-Id", "findit")
            .addHeader("X-Parse-Master-Key", "finditkey")
            .post(body)
            .build()
        try {
            val response = client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    // Handle this
                }

                override fun onResponse(call: Call, response: Response) {
                    Log.i("response", response.toString())
                }
            })
        } catch (e: IOException) {

        }
    }

    private fun mapToJson(title_message: String, alert_message: String, username: String): String {
        val json = JSONObject()

        val list_channels = JSONArray()
        list_channels.put("AvailableUser")

        json.put("channels", list_channels)

        val data = JSONObject()

        data.put("alert", alert_message)
        data.put("title", title_message)
        data.put("user", username)

        json.put("data", data)

        Log.i("json", json.toString())
        """
        '{
        "channels": [
        "AvailableUser"
        ],
        "data": {
            "alert": "The Giants won against the Mets 2-3.",
            "tittle": "The Giants won against the Mets 2-3."
        }
    }'
    """
        return json.toString()
    }


}