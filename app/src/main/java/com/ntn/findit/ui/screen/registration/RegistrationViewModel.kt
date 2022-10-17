package com.ntn.findit.ui.screen.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class RegistrationViewModel : ViewModel() {
    private val _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _passwordConfirmation = MutableLiveData<String>()
    val passwordConfirmation: LiveData<String> = _passwordConfirmation

    private val _registrationEnable = MutableLiveData<Boolean>()
    val registrationEnable: LiveData<Boolean> = _registrationEnable

    fun onUsernameChange(username: String) {
        _username.value = username
        onRegistrationChange()
    }

    fun onEmailChange(username: String) {
        _email.value = username
        onRegistrationChange()
    }

    fun onPasswordChange(password: String) {
        _password.value = password
        onRegistrationChange()
    }

    fun onPasswordConfirmationChange(password: String) {
        _passwordConfirmation.value = password
        onRegistrationChange()
    }

    fun onRegistrationChange() {
        _registrationEnable.value = validateFields()
    }

    private fun validateFields() =
        username.value?.isNotEmpty() == true &&
                password.value?.isNotEmpty() == true &&
                email.value?.isNotEmpty() == true &&
                passwordConfirmation.value?.isNotEmpty() == true &&
                password.value == passwordConfirmation.value

}