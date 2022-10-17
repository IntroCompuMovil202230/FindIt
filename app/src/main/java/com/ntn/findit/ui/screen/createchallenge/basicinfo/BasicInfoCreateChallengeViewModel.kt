package com.ntn.findit.ui.screen .createchallenge.basicinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BasicInfoCreateChallengeViewModel: ViewModel() {
    private val _clueName = MutableLiveData<String>()
    val clueName: LiveData<String> = _clueName

    private val _description = MutableLiveData<String>()
    val description: LiveData<String> = _description

    private val _continueEnable = MutableLiveData<Boolean>()
    val continueEnable: LiveData<Boolean> = _continueEnable

    fun onClueNameChange(clueName: String){
        _clueName.value = clueName
        onFormChange()
    }

    fun onDescriptionChange(description: String){
        _description.value = description
        onFormChange()
    }

    fun onFormChange(){
        _continueEnable.value = checkFields()
    }
    private fun checkFields() =
         _clueName.value?.isNotEmpty() == true && _description.value?.isNotEmpty() == true
}
