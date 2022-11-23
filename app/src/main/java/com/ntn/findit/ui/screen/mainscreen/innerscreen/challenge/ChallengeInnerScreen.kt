package com.ntn.findit.ui.screen.mainscreen.innerscreen.challenge

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ntn.findit.R
import com.ntn.findit.model.Challenge
import com.ntn.findit.ui.navigation.Graph
import com.ntn.findit.ui.screen.shared.CustomSpacer


@Composable
fun ChallengeInnerScreen(
    navController: NavController,
    _viewModel: ChallengeInnerViewModel = viewModel()
) {
    _viewModel.requestIP(LocalContext.current)
    val challenge by _viewModel.challenges.collectAsState()
    Column(
        modifier = Modifier.padding(top = 15.dp, start = 25.dp, end = 25.dp, bottom = 75.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //SearchBar()
        WeatherViewer()
        CustomSpacer()
        val scrollState = rememberScrollState()
        Column(modifier = Modifier.verticalScroll(scrollState)) {
            CarouselTitled(
                title = "Desafíos para ti",
                navController = navController,
                listChallenge = challenge
            )
            CustomSpacer(15.0)
        }
    }
}

@Composable
fun CarouselTitled(
    navController: NavController,
    title: String,
    listChallenge: MutableList<Challenge>
) {
    Column(verticalArrangement = Arrangement.spacedBy(7.dp)) {
        Text(text = title)
        CustomCardSlider(navController, listChallenge)
    }
}

@Composable
fun CustomCardSlider(navController: NavController, listChallenge: MutableList<Challenge>) {
    LazyRow(
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        //Log.i("row", challengeList.size.toString())
        items(listChallenge) { obj ->
            run {
                CustomCard(navController, obj)
            }
        }
    }
}

@Composable
fun CustomCard(navController: NavController, challenge: Challenge) {
    Card(
        shape = RoundedCornerShape(19.dp),
        backgroundColor = Color.White,
        modifier = Modifier
            .width(180.dp)
    ) {
        Column {
            Box(modifier = Modifier.fillMaxWidth()) {
                challenge.bitmap?.let {
                    Image(
                        bitmap = it.asImageBitmap(),
                        contentDescription = "",
                        alignment = Alignment.Center,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Column(modifier = Modifier.padding(horizontal = 12.dp)) {
                CustomSpacer(10.0)
                Text(text = challenge.name, maxLines = 1)
                CustomSpacer(10.0)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = challenge.rating.toString(), fontSize = 18.sp)
                    Icon(imageVector = Icons.Default.Star, contentDescription = "")
                    Spacer(modifier = Modifier.weight(1f))
                    OutlinedButton(onClick = {
                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            key = "challenge",
                            value = challenge
                        )
                        navController.navigate(Graph.GAME)
                    }) {
                        Text(text = "Jugar")
                    }
                }
                CustomSpacer(5.0)
            }
        }
    }
}

@Composable
fun WeatherViewer(_viewModel: ChallengeInnerViewModel = viewModel()) {
    val weather by _viewModel.weather.collectAsState()
    Card(
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.primary,
        shape = RoundedCornerShape(10.dp),
    ) {
        Column(
            modifier = Modifier.padding(25.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "El Clima en tu zona", fontSize = 25.sp, fontWeight = FontWeight.ExtraBold)
            Spacer(modifier = Modifier.padding(15.dp))
            Row {

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Temp", fontSize = 15.sp, fontWeight = FontWeight.ExtraBold)
                    Text(
                        text = "${weather.first}°C",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Rain", fontSize = 15.sp, fontWeight = FontWeight.ExtraBold)
                    Text(
                        text = "${weather.second}mm",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Humedad", fontSize = 15.sp, fontWeight = FontWeight.ExtraBold)
                    Text(
                        text = "${weather.third}%",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
        }
    }
}

fun NavHostController.navigateAndClean(route: String) {
    navigate(route = route) {
        popUpTo(graph.startDestinationId) { inclusive = true }
    }
    graph.setStartDestination(route)
}

@Composable
@Preview(showSystemUi = true)
fun Preview() {
    ChallengeInnerScreen(rememberNavController())
}