package com.ntn.findit.ui.screen.createchallenge.addclue

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddClueViewModel : ViewModel() {
    private val _clueName = MutableLiveData<String>()
    val clueName = _clueName

    private val _clueContent = MutableLiveData<String>()
    val clueContent = _clueContent

    private val _points = MutableLiveData<Int>()
    val points = _points

    private val _createEnabled = MutableLiveData<Boolean>()
    val createEnabled = _createEnabled

    fun onClueNameChange(name: String){
        _clueName.value = name
        onFormChange()
    }

    fun onContentChange(content:String){
        _clueContent.value = content
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


    private fun onFormChange() {
        _createEnabled.value =
            _clueName.value?.isNotEmpty() == true && _clueContent.value?.isNotEmpty() == true
    }

}