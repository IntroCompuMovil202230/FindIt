package com.ntn.findit.ui.screen.createchallenge.addclue

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.storage.FirebaseStorage
import com.parse.ParseObject
import com.parse.ParseQuery
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AddClueViewModel : ViewModel() {
    private val _clueName = MutableLiveData<String>()
    val clueName = _clueName

    private val _question = MutableStateFlow<String>("")
    val question = _question

    private val _answer = MutableStateFlow<String>("")
    val answer = _answer

    private val _content = MutableStateFlow<String>("")
    val content = _content

    private val _points = MutableLiveData<Int>()
    val points = _points

    private val _image = MutableStateFlow<Uri?>(null)
    val image = _image.asStateFlow()

    fun setImage(image: Uri) {
        _image.value = image
    }

    private val _createEnabled = MutableLiveData<Boolean>()
    val createEnabled = _createEnabled

    private val _expanded = MutableStateFlow(false)
    val expanded = _expanded.asStateFlow()

    fun setExpanded(expanded: Boolean) {
        _expanded.value = expanded
    }

    private val _selectedTypeText = MutableStateFlow("Texto")
    val selectedTypeText = _selectedTypeText.asStateFlow()

    fun setSelectedTypeText(selectedtype: String) {
        _selectedTypeText.value = selectedtype
        onFormChange()
    }

    fun onClueNameChange(name: String){
        _clueName.value = name
        onFormChange()
    }
    fun setQuestion(question:String) {
        _question.value = question
        onFormChange()
    }
    fun setAnswer(answer:String) {
        _answer.value = answer
        onFormChange()
    }

    fun setContent(content:String){
        _content.value = content
        onFormChange()
    }


    fun onPointsChange(points:Int){
        _points.value = points
    }

    fun onModifyPoints(add: Int){
        Log.d("Mio", "hereeeeeee")
        if (add == -1 && _points.value == 0){
            return
        }
        if(add == 1 && points.value == 10){
            return
        }
        _points.value = _points.value?.plus(add)
    }


    fun onFormChange() {
        if(selectedTypeText.value == "Imagen"){
            _createEnabled.value = _image.value != null == true
        }
        else if(selectedTypeText.value == "Texto"){
            _createEnabled.value = !_content.value.isNullOrEmpty() == true
        }
        else if(selectedTypeText.value == "Pregunta"){
            _createEnabled.value = !_answer.value.isNullOrEmpty() && !_question.value.isNullOrEmpty() == true
        }
    }

    fun uploadImage(image:Uri) {

        //Firebase firestore
        // Create a storage reference from our app
        val storageRef = FirebaseStorage.getInstance().reference

        // Create a reference to 'images/mountains.jpg'
        val uploadTask = storageRef.child("clues/"+_clueName.value+".jpg").putFile(image)
        uploadTask.addOnSuccessListener {
            Log.e("Firebase", "Image Upload success")
        }.addOnFailureListener {
            Log.e("Firebase", "Image Upload fail")
            //       mProgressDialog.dismiss()
        }
    }

    fun save(challengeName: String){
        viewModelScope.launch {
            val query = ParseQuery.getQuery<ParseObject>("Clue")
            _clueName?.value?.let { query.whereEqualTo("name",  it) }
            query.countInBackground().onSuccess { it ->
                if (it.result == 0) {
                    val ob = ParseObject("Clue")
                    if (selectedTypeText.value == "Imagen") {
                        ob.put("type", "Imagen")
                        _image.value?.let { uploadImage(it) }
                    } else if (selectedTypeText.value == "Texto") {
                        ob.put("type", "Texto")
                        ob.put("text", _content.value)
                    } else if (selectedTypeText.value == "Pregunta") {
                        ob.put("type", "Pregunta")
                        ob.put("question", _question.value)
                        ob.put("answer", _answer.value)
                    }
                    _clueName?.value?.let { ob.put("name", it) }
                    ob.put("challengeName", challengeName)
                    ob.save()
                }
            }
        }
    }



}