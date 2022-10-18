package com.ntn.findit.ui.screen.createchallenge.basicinfo

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BasicInfoCreateChallengeViewModel: ViewModel() {
    private val _clueName = MutableLiveData<String>()
    val clueName: LiveData<String> = _clueName

    private val _description = MutableLiveData<String>()
    val description: LiveData<String> = _description

    private val _imageUri = MutableLiveData<Uri?>()
    val imageUri = _imageUri

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

    private fun onFormChange(){
        _continueEnable.value = checkFields()
    }
    private fun checkFields() =
         _clueName.value?.isNotEmpty() == true && _description.value?.isNotEmpty() == true && _imageUri.value != null

    fun onUriChange(uri: Uri) {
        Log.d("Mio", "URI: ${uri}")
        _imageUri.value = uri
        onFormChange()
    }
}
