package com.ntn.findit.ui.screen.registration

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ntn.findit.ui.navigation.AppScreens
import com.ntn.findit.ui.screen.shared.*
import kotlinx.coroutines.launch

@Composable
fun RegistrationScreen(
    navController: NavController,
    _viewModel: RegistrationViewModel = viewModel()
) {
    val username: String by _viewModel.username.observeAsState("")
    val email: String by _viewModel.email.observeAsState("")
    val password: String by _viewModel.password.observeAsState("")
    val passwordConfirmation: String by _viewModel.passwordConfirmation.observeAsState("")
    Column(modifier = Modifier.padding(vertical = 45.dp, horizontal = 25.dp)) {
        Title()
        CustomSpacer()
        Text(text = "Nombre de usuario", fontWeight = FontWeight.Bold)
        CustomSpacer(5.0)
        CustomOutlinedTextField(
            hint = "Nombre",
            value = username,
            onTextChange = { _viewModel.onUsernameChange(it) })
        CustomSpacer()
        Text(text = "Correo electrónico", fontWeight = FontWeight.Bold)
        CustomSpacer(5.0)
        CustomOutlinedTextField(
            hint = "Email",
            value = email,
            onTextChange = { _viewModel.onEmailChange(it) }
        )
        CustomSpacer()
        TitledInputConfirmation(
            title = "Contraseña",
            hint = "Crear Contraseña",
            hint2 = "Confirmar Contraseña",
            value1 = password,
            listener1 = { _viewModel.onPasswordChange(it) },
            value2 = passwordConfirmation,
            listener2 = { _viewModel.onPasswordConfirmationChange(it) }
        )
        CustomSpacer(35.0)
        val coroutineScope = rememberCoroutineScope()
        val context = LocalContext.current
        ConfirmationSection {
            coroutineScope.launch {
                _viewModel.onRegistrationRequest()
            }.invokeOnCompletion {
                if (_viewModel.registrationSuccess.value == true) {
                    navController.navigate(AppScreens.MainScreen.route) {
                        popUpTo(AppScreens.LoginScreen.route) {
                            inclusive = true
                        }
                    }
                } else {
                    Toast.makeText(context, "Error SigningUp, try, again", Toast.LENGTH_LONG).show()
                }
            }
        }

    }
}

@Composable
fun Title() {
    Column {
        Text(text = "Registrarse", fontWeight = FontWeight.ExtraBold, fontSize = 15.sp)
        CustomSpacer(5.0)
        Text(
            text = "Registrese para poder empezar",
            fontWeight = FontWeight.Light,
            fontSize = 11.sp
        )
    }
}

@Composable
fun TitledInputColumn(input: @Composable (() -> Unit)) {
    Column {
        input()
    }
}

@Composable
fun TitledInputConfirmation(
    title: String,
    hint: String,
    hint2: String,
    value1: String,
    value2: String,
    listener1: (String) -> Unit,
    listener2: (String) -> Unit
) {
    TitledInputColumn {
        TitledInput(title = title, hint = hint, value = value1, listener = listener1)
        CustomPasswordOutlinedTextField(hint = hint2, value = value2, onTextChange = listener2)
    }
}

@Composable
fun TitledInput(title: String, hint: String, value: String, listener: (String) -> Unit) {
    Text(text = title, fontWeight = FontWeight.Bold)
    CustomSpacer(5.0)
    CustomPasswordOutlinedTextField(hint = hint, value = value, onTextChange = listener)
}


@Composable
fun ConfirmationSection(_viewModel: RegistrationViewModel = viewModel(), buttonAction: () -> Unit) {
    val enabled: Boolean by _viewModel.registrationEnable.observeAsState(false)
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomButton("Registrarse", enabled = enabled, buttonAction)
        Spacer(modifier = Modifier.padding(5.dp))
        Row {
            Text(text = "¿Ya tienes una cuenta?")
            CustomSpacer(1.0)
            CustomClickableText("Inicia Sesión", {})
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun Preview() {
    RegistrationScreen(rememberNavController())
}