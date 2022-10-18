package com.ntn.findit.ui.screen.biometricAuth

import androidx.biometric.BiometricManager
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ntn.findit.ui.screen.biometricAuth.ui.theme.FindItTheme

class BioAuthActivity : AppCompatActivity() {
    private var canAuth = false
    private var _auth = MutableLiveData<Boolean>()
    var auth: LiveData<Boolean> = _auth
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FindItTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Auth(authenticateBiometric())
                }
            }
        }
        setAuth()
    }
    private fun setAuth(){
        if(BiometricManager.from(applicationContext).canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG or  BiometricManager.Authenticators.DEVICE_CREDENTIAL) == BiometricManager.BIOMETRIC_SUCCESS)
            canAuth = true
        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Autenticacion Biometrica")
            .setSubtitle("Autenticacion con sensor biometrico")
            .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG
            or BiometricManager.Authenticators.DEVICE_CREDENTIAL)
            .build()
    }
    private fun authenticateBiometric(): (Boolean) -> Unit {
        if(canAuth){
            BiometricPrompt(this,ContextCompat.getMainExecutor(this),object: BiometricPrompt.AuthenticationCallback(){
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    auth(true)
                }
            }).authenticate(promptInfo)
        }
        else
            auth(true)
    }


}




