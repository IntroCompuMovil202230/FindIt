package com.ntn.findit.ui.screen.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parse.ParseException
import com.parse.ParseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class LoginViewModel : ViewModel() {

    private val _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _loginEnable = MutableLiveData<Boolean>()
    val loginEnable: LiveData<Boolean> = _loginEnable

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess = _loginSuccess

    fun onUsernameChange(username: String) {
        _username.value = username
        onLoginChange()
    }

    fun onPasswordChange(password: String) {
        _password.value = password
        onLoginChange()
    }

    private fun onLoginChange() {
        _loginEnable.value = validateFields()
    }

    private fun validateFields() =
        username.value?.isNotEmpty() == true && password.value?.isNotEmpty() == true


    fun login(){
        try {
            ParseUser.logIn(_username.value, _password.value)
            _loginSuccess.value = true
        }catch (e: ParseException){

        }


    }

}