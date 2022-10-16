package com.ntn.findit.ui.screen.registration

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ntn.findit.ui.screen.shared.*

@Composable
fun RegistrationScreen(){
    Column(modifier = Modifier.padding(vertical = 45.dp, horizontal = 25.dp)){
        Title()
        CustomSpacer()
        TitledInputColumn {
            TitledInput(title = "Nombre de usuario", hint = "Nombre")
        }
        CustomSpacer()
        TitledInputColumn{
            TitledInput(title = "Correo Electrónico", hint = "Correo")
        }
        CustomSpacer()
        TitledInputConfirmation(title = "Contraseña", hint = "Crear Contraseña", hint2 = "Confirmar Contraseña")
        CustomSpacer(35.0)
        ConfirmationSection()
    }
}

@Composable
fun Title(){
    Column {
        Text(text = "Registrarse", fontWeight = FontWeight.ExtraBold, fontSize = 15.sp)
        CustomSpacer(5.0)
        Text(text = "Registrese para poder empezar", fontWeight = FontWeight.Light, fontSize = 11.sp)
    }
}

@Composable
fun TitledInputColumn(input: @Composable (() -> Unit)){
    Column {
        input()
    }
}

@Composable
fun TitledInputConfirmation(title: String, hint:String, hint2: String){
    TitledInputColumn {
        TitledInput(title = title, hint = hint)
        CustomPasswordOutlinedTextField(hint = hint2)
    }
}

@Composable
fun TitledInput(title: String, hint: String){
    Text(text = title, fontWeight = FontWeight.Bold)
    CustomSpacer(5.0)
    CustomPasswordOutlinedTextField(hint = hint)
}


@Composable
fun ConfirmationSection() {
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        CustomButton("Registrarse")
        Spacer(modifier = Modifier.padding(5.dp))
        Row {
            Text(text = "¿Ya tienes una cuenta?")
            CustomSpacer(1.0)
            CustomClickableText("Inicia Sesión")
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun Preview(){
    RegistrationScreen()
}