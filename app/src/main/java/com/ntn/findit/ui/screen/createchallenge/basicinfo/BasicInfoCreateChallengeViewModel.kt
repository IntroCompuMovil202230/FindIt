package com.ntn.findit.ui.screen.createchallenge.basicinfo

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.storage.FirebaseStorage
import com.ntn.findit.ui.screen.createchallenge.SharedViewModel

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
    fun uploadImage(image: Uri) {

        //Firebase firestore
        // Create a storage reference from our app
        val storageRef = FirebaseStorage.getInstance().reference

        // Create a reference to 'images/mountains.jpg'
        val uploadTask = storageRef.child("challenges/"+clueName.value+".jpg").putFile(image)
        uploadTask.addOnSuccessListener {
            Log.e("Firebase", "Image Upload success")
        }.addOnFailureListener {
            Log.e("Firebase", "Image Upload fail")
            //       mProgressDialog.dismiss()
        }
    }
}
