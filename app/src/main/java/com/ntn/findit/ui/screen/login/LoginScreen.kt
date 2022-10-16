package com.ntn.findit.ui.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ntn.findit.R
import com.ntn.findit.ui.screen.shared.*

@Composable
fun LoginScreen(){
    Column( modifier = Modifier.padding(vertical = 45.dp, horizontal = 25.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        LogoImage()
        CustomSpacer(18.0)
        LoginFormulary()
        CustomSpacer(14.0)
        FootSection()
    }

}

@Composable
fun LogoImage(){
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Image(painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Logo",
        )
    }
}
@Composable
fun LoginFormulary(){
    Text(text = "Iniciar sesión", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
    CustomOutlinedTextField(hint = "Correo electrónico")
    CustomPasswordOutlinedTextField(hint = "Contraseña")
    CustomClickableText(text = "Olvidaste la contraseña?")
}

@Composable
fun FootSection(){
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        CustomButton(text = "Iniciar")
        Row (){
            Text(text = "¿No tienes cuenta?")
            CustomSpacer(2.0)
            CustomClickableText(text = "Registrate Ahora")
        }
    }
}




@Composable
@Preview(showSystemUi = true)
fun Preview(){
    LoginScreen()
}