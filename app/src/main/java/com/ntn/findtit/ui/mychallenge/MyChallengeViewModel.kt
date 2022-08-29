package com.ntn.findtit.ui.mychallenge

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyChallengeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is My CHallenge Fragment"
    }
    val text: LiveData<String> = _text
}