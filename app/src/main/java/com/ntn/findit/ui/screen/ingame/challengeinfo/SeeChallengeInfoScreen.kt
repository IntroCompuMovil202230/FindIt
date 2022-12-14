package com.ntn.findit.ui.screen.ingame.challengeinfo

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ntn.findit.R
import com.ntn.findit.ui.navigation.Graph
import com.ntn.findit.ui.screen.shared.CustomSpacer
import com.ntn.findit.ui.theme.LightWhite

@Composable
fun SeeChallengeInfo(navController: NavController) {
    val scrollState = rememberScrollState()
    Column(modifier = Modifier.verticalScroll(scrollState)) {
        Head()
        Column(Modifier.padding(vertical = 25.dp, horizontal = 25.dp)) {
            Body("Busqueda Pirata","Description","Juan","5.0")
            CustomSpacer(14.0)
            Body2("12")
            CustomSpacer(14.0)
            Foot(navController)
        }
    }
}

@Composable
fun Head() {
    Box(modifier = Modifier.fillMaxWidth()) {
        Image(
            contentScale = ContentScale.FillWidth,
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "",
            modifier = Modifier.fillMaxWidth()
        )
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "")
        }
    }
}

@Composable
fun Body(name:String,desc:String,creator:String,rating:String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Column {
            Text(text = name, fontSize = 16.sp, fontWeight = FontWeight.ExtraBold)
            CustomSpacer(3.0)
            Text(text = desc)
        }
        Spacer(modifier = Modifier.weight(1f))
        Column {
            Row {
                Text(text = rating, fontSize = 18.sp)
                Icon(imageVector = Icons.Default.Star, contentDescription = "")
            }
            Text(text = "Creador: @"+creator)
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.AddCircle, contentDescription = "")
        }
    }
}

@Composable
fun Body2(numClues:String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15),

        ) {
        Column(
            modifier = Modifier
                .background(LightWhite)
                .padding(horizontal = 27.dp, vertical = 3.dp)
        ) {
            Text(
                text = "Caracter??sticas del desaf??o",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,
                modifier = Modifier.background(Color.Transparent),
            )
            CustomSpacer(10.0)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.Star, contentDescription = "")
                CustomSpacer(4.0)
                Text(text = "Pista principal")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.Star, contentDescription = "")
                CustomSpacer(4.0)
                Text(text = numClues+" Pista Secundarias")
            }
            CustomSpacer(10.0)
        }
    }
}

@Composable
fun Foot(navController: NavController) {
    Column {
        Row() {
            //CustomRadioGroup()
            Button(
                onClick = { navController.navigate(Graph.GAME) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20)
            ){
            Icon(imageVector = Icons.Default.PlayArrow, contentDescription = "")
            CustomSpacer(3.0)
            Text(text = "Jugar")
        }
    }
}
}

@Composable
fun CustomRadioGroup() {
    /*TODO*/
}


@Composable
@Preview(showSystemUi = true, name = "See challenge info")
fun Preview() {
    SeeChallengeInfo(rememberNavController())
}