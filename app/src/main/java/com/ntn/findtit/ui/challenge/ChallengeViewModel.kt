package com.ntn.findtit.ui.challenge

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChallengeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Challenge Fragment"
    }
    val text: LiveData<String> = _text
}