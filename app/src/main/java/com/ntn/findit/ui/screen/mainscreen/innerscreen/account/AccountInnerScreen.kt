package com.ntn.findit.ui.screen.mainscreen.innerscreen.account

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.ntn.findit.R
import com.ntn.findit.ui.screen.shared.CustomSpacer

@Composable
fun AccountInnerScreen() {
    Column(
        Modifier.padding(vertical = 45.dp, horizontal = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleHeader()
        CustomSpacer(25.0)
        AccountBody()
    }
}

@Composable
fun TitleHeader() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Cuenta", fontWeight = FontWeight.ExtraBold, fontSize = 18.sp)
        CustomSpacer(7.0)
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "",
            modifier = Modifier.clip(CircleShape)
        )
        CustomSpacer()
        Text(text = "LucasScoot", fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)

    }
}

@Composable
fun AccountBody() {
    val scrollState = rememberScrollState()
    Column {
        Text(
            text = "Perfil",
            fontWeight = FontWeight.Bold,
            fontSize = 27.sp,
            modifier = Modifier.padding(horizontal = 8.dp),
        )
        CustomSpacer(9.0)
        Divider(color = Color.LightGray, thickness = 1.dp)
        CustomSpacer(9.0)
        Column(modifier = Modifier.verticalScroll(scrollState)) {
            CustomInnerRow(text = "Nombre de usuario")
            CustomInnerRow(text = "Puntaje Total")
            CustomInnerRow(text = "Ranking Global")
            CustomInnerRow(text = "Desafíos Creados")
            CustomInnerRow(text = "Pistas Creadas")
            CustomInnerRow(text = "Desafíos Completados")
            CustomInnerRow(text = "Pistas Completadas")
        }
    }
}


@Composable
private fun CustomInnerRow(text: String) {
    Row{
        Text(
            text = text,
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Lucas Scoot",
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
    
    CustomSpacer(9.0)
    Divider(color = Color.LightGray, thickness = 1.dp)
    CustomSpacer(9.0)
}

@Composable
@Preview(showSystemUi = true, name = "Account Screen")
fun Preview() {
    AccountInnerScreen()
}