package com.ntn.findit.ui.screen.createchallenge.location

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.ntn.findit.ui.screen.shared.CustomSpacer

@Composable
fun LocationCreateChallengeScreen() {
    Column(Modifier.padding(vertical = 45.dp, horizontal = 25.dp)
    , horizontalAlignment = Alignment.CenterHorizontally) {
        LinearProgressIndicator(progress = 0.4f, modifier = Modifier.fillMaxWidth())
        CustomSpacer(15.0)
        Title()
        CustomSpacer(15.0)
        SearchBar()
        CustomSpacer(5.0)
        MiniMap()
        Spacer(modifier = Modifier.weight(1f))
        Foot()
    }
}


@Composable
fun Title() {
    Text(text = "Ubicación Objetivo", fontWeight = FontWeight.ExtraBold, fontSize = 30.sp)
    CustomSpacer(10.0)
    Text(text = "Elige o ingresa una ubicación para el objetivo")

}

@Composable
fun SearchBar() {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = { Icon(Icons.Default.Search, "") },
        shape = RoundedCornerShape(50),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.LightGray, //TODO Change color
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun MiniMap() {
    Box(modifier = Modifier
        .size(300.dp)
        .fillMaxWidth()) {
        GoogleMap(
            googleMapOptionsFactory = {
                GoogleMapOptions().mapId("MyMapId")
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun Foot() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.LocationOn, contentDescription = "")
            CustomSpacer(2.0)
            Text(text = "Seleccionar mi ubicación actual")
        }
        CustomSpacer(5.0)
        Row {
            OutlinedButton(onClick = { /*TODO*/ }) {
                Text(text = "Anterior")
            }
            CustomSpacer(30.0)
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Siguiente")
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun Preview() {
    LocationCreateChallengeScreen()
}