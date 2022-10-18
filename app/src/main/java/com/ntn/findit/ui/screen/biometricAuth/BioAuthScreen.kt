package com.ntn.findit.ui.screen.biometricAuth

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.lifecycle.LiveData

@Composable
fun Auth(auth:LiveData<Boolean>) {
    Column(

    ){
        Text(if (auth) "Estas Autenticado" else "no estas autenticado")
        Button(onClick = {

            }
        }) {
            Text(if (auth) "Estas Autenticado" else "no estas autenticado")
        }
    }
}
