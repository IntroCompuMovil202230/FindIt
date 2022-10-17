package com.ntn.findit.ui.screen.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.parse.ParseUser


class LoginViewModel : ViewModel() {

    private val _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _loginEnable = MutableLiveData<Boolean>()
    val loginEnable: LiveData<Boolean> = _loginEnable

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


    fun login(): Boolean{
        var success : Boolean = false
        ParseUser.logInInBackground(username.value.toString(), password.value.toString()) { user, e ->
            if (user != null) {
                Log.d("Mio", "Signed successfull" + username.value.toString() +password.value.toString() )
                success = true
            } else {
                Log.d("Mio", "Failed log" + username.value.toString() + password.value.toString())
                Log.d("Mio", e.toString())
            }
        }
        return success

    }

}