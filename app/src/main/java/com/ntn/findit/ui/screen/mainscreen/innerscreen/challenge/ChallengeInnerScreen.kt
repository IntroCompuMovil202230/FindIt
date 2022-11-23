package com.ntn.findit.ui.screen.mainscreen.innerscreen.challenge

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Games
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.rememberNavController
import com.ntn.findit.R
import com.ntn.findit.model.Challenge
import com.ntn.findit.ui.navigation.Graph
import com.ntn.findit.ui.screen.createchallenge.createclues.ItemClue
import com.ntn.findit.ui.screen.shared.CustomSpacer
import com.ntn.findit.ui.screen.shared.SearchBarField
import com.parse.ParseUser


@Composable
fun ChallengeInnerScreen(navController: NavController, _viewModel:ChallengeInnerViewModel = viewModel()) {

 //   _viewModel.getClues()
    Column(
        modifier = Modifier.padding(top = 15.dp, start = 25.dp, end = 25.dp, bottom = 75.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBar()
        CustomSpacer()
        val scrollState = rememberScrollState()
        Column(modifier = Modifier.verticalScroll(scrollState)) {
            CarouselTitled(title = "Desafíos para ti", navController = navController, _viewModel = _viewModel
            )
            CustomSpacer(15.0)
          //  CarouselTitled(title = "Favoritos", navController = navController, _viewModel = _viewModel)
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
fun CarouselTitled(navController: NavController,title: String,_viewModel:ChallengeInnerViewModel) {
    Column(verticalArrangement = Arrangement.spacedBy(7.dp)) {
        Text(text = title)
        CustomCardSlider(navController,_viewModel)
    }
}

@Composable
fun CustomCardSlider(navController: NavController,_viewModel:ChallengeInnerViewModel) {
    _viewModel.getClues()
    val scrollState = rememberScrollState()
    val challengeList by  _viewModel.challenges.collectAsState()
    LazyRow(
       // modifier = Modifier.fillMaxHeight(),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
/*
        items(challengeList) { challenge ->
            CustomCard(navController,challenge)
        }
*/Log.i("row",challengeList.size.toString())
        items(challengeList) {  obj ->
            run {
                CustomCard(navController,obj)
            }
        }
        /*
        CustomCard(navController)
        CustomCard(navController)
        CustomCard(navController)
        CustomCard(navController)
        CustomCard(navController)
        CustomCard(navController)
        CustomCard(navController)

         */
    }
}

@Composable
fun CustomCard(navController: NavController,challenge: Challenge) {
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
                        navController.currentBackStackEntry?.savedStateHandle?.set(key="challenge",value=challenge)
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