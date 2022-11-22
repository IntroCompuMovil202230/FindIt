package com.ntn.findit.ui.screen.createchallenge.createclues

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ntn.findit.model.Challenge
import com.ntn.findit.model.Clue
import com.parse.ParseObject
import com.parse.ParseQuery
import com.parse.ParseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class ClueCreateChallengeViewModel: ViewModel() {
    private val _clues = MutableStateFlow<MutableList<Clue>>(mutableStateListOf())
    val clues = _clues.asStateFlow()



    fun getClues(challengeName:String){
        val query = ParseQuery.getQuery<ParseObject>("Clue")
        Log.i("consultado",challengeName)
        query.whereEqualTo("challengeName", challengeName)
        query.findInBackground().onSuccess { listResult ->
            listResult.result.forEach {
                val name = it.getString("name")
                val type = it.getString("type")

                if (name != null && type!= null){
                    Log.i("clue"+name,name+" "+type)
                    clues.value.add(
                        Clue(
                            name = name,
                            type = type
                        )
                    )
                }

            }
        }
        query.countInBackground().onSuccess{it->
            Log.i("resultado",it.result.toString())
        }
    }
}
