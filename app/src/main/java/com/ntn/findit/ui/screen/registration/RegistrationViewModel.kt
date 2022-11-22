package com.ntn.findit.ui.screen.registration

import android.net.Uri
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.storage.FirebaseStorage
import com.parse.Parse
import com.parse.ParseException
import com.parse.ParseUser
import com.parse.SignUpCallback


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

    private val _registrationSuccess = MutableLiveData<Boolean>()
    val registrationSuccess: LiveData<Boolean> = _registrationSuccess

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

    private fun onRegistrationChange() {
        _registrationEnable.value = validateFields()
    }

    private fun validateFields() =
        username.value?.isNotEmpty() == true &&
                password.value?.isNotEmpty() == true &&
                email.value?.isNotEmpty() == true &&
                passwordConfirmation.value?.isNotEmpty() == true &&
                password.value == passwordConfirmation.value && Patterns.EMAIL_ADDRESS.matcher(email.value.toString()).matches()

    fun onRegistrationRequest() {
        val user = ParseUser();
        user.username = username.value
        user.setPassword(password.value)
        user.email = email.value
        try{
            user.signUp()
            _registrationSuccess.value = true
        }catch (e: ParseException){}

    }



}