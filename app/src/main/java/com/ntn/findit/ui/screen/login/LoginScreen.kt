package com.ntn.findit.ui.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ntn.findit.R
import com.ntn.findit.ui.navigation.AppScreens
import com.ntn.findit.ui.screen.shared.*

@Composable
fun LoginScreen(navController: NavController, _viewModel: LoginViewModel = viewModel()) {

    Column(
        modifier = Modifier.padding(vertical = 45.dp, horizontal = 25.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LogoImage()
        CustomSpacer(18.0)
        LoginFormulary(_viewModel)
        CustomSpacer(14.0)
        FootSection(
            _viewModel,
            buttonAction = {
                val next = _viewModel.login()
                if (next) {
                    navController.navigate(AppScreens.MainScreen.route)
                }
            },
            textActionClick = { navController.navigate(AppScreens.RegistrationScreen.route) },
        )
    }

}

@Composable
fun LogoImage() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Logo",
        )
    }
}

@Composable
fun LoginFormulary(_viewModel: LoginViewModel) {
    val username: String by _viewModel.username.observeAsState("")
    val password: String by _viewModel.password.observeAsState("")
    Text(text = "Iniciar sesión", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
    CustomOutlinedTextField(
        hint = "Correo electrónico",
        value = username,
        onTextChange = { _viewModel.onUsernameChange(it) }
    )
    CustomPasswordOutlinedTextField(
        hint = "Contraseña",
        value = password,
        onTextChange = { _viewModel.onPasswordChange(it) }
    )
    CustomClickableText(text = "Olvidaste la contraseña?") {}
}

@Composable
fun FootSection(_viewModel: LoginViewModel, buttonAction: () -> Unit, textActionClick: () -> Unit) {
    val enabled: Boolean by _viewModel.loginEnable.observeAsState(false)
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomButton(text = "Iniciar", enabled, onClick = buttonAction)
        Row {
            Text(text = "¿No tienes cuenta?")
            CustomSpacer(2.0)
            CustomClickableText(text = "Registrate Ahora", textActionClick)
        }
    }
}


@Composable
@Preview(showSystemUi = true)
fun Preview() {
    LoginScreen(navController = rememberNavController())
}