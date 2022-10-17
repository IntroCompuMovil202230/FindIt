package com.ntn.findit.ui.screen.login

import androidx.lifecycle.*
import androidx.navigation.NavController
import com.ntn.findit.ui.navigation.AppScreens


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

}