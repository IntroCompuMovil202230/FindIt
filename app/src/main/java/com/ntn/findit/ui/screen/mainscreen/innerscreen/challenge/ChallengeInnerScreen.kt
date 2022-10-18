package com.ntn.findit.ui.screen.mainscreen.innerscreen.challenge

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Games
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.ntn.findit.R
import com.ntn.findit.ui.screen.shared.CustomSpacer
import com.ntn.findit.ui.screen.shared.SearchBarField


@Composable
fun ChallengeInnerScreen() {
    Column(
        modifier = Modifier.padding(top = 15.dp, start = 25.dp, end = 25.dp, bottom = 75.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBar()
        CustomSpacer()
        val scrollState = rememberScrollState()
        Column(modifier = Modifier.verticalScroll(scrollState)) {
            CarouselTitled(title = "Desafíos para ti")
            CustomSpacer(15.0)
            CarouselTitled(title = "Favoritos")
        }
    }
}

@Composable
fun SearchBar() {
    Row {
        SearchBarField()
        TextButton(onClick = { /*TODO*/ }) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Ranking", fontSize = 9.sp)
                Icon(
                    imageVector = Icons.Default.Games,
                    contentDescription = ""
                )
            }
        }
    }
}

@Composable
fun CarouselTitled(title: String) {
    Column(verticalArrangement = Arrangement.spacedBy(7.dp)) {
        Text(text = title)
        CustomCardSlider()
    }
}

@Composable
fun CustomCardSlider() {
    val scrollState = rememberScrollState()
    Row(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier.horizontalScroll(scrollState)
    ) {
        CustomCard()
        CustomCard()
        CustomCard()
        CustomCard()
        CustomCard()
        CustomCard()
        CustomCard()
    }
}

@Composable
fun CustomCard() {
    Card(
        shape = RoundedCornerShape(19.dp),
        backgroundColor = Color.White,
        modifier = Modifier
            .width(180.dp)
    ) {
        Column {
            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "",
                    alignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
            }

            Column(modifier = Modifier.padding(horizontal = 12.dp)) {
                CustomSpacer(10.0)
                Text(text = "Prueba", maxLines = 1)
                CustomSpacer(10.0)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "5.0", fontSize = 18.sp)
                    Icon(imageVector = Icons.Default.Star, contentDescription = "")
                    Spacer(modifier = Modifier.weight(1f))
                    OutlinedButton(onClick = { /*TODO*/ }) {
                        Text(text = "Jugar")
                    }
                }
                CustomSpacer(5.0)
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun Preview() {
    ChallengeInnerScreen()
}