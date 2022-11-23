package com.ntn.findit.ui.screen.createchallenge.challengepreview

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ntn.findit.R
import com.ntn.findit.ui.screen.createchallenge.CreateChallenge
import com.ntn.findit.ui.screen.ingame.challengeinfo.Body
import com.ntn.findit.ui.screen.ingame.challengeinfo.Body2
import com.ntn.findit.ui.screen.mainscreen.navigation.BottomNavigationBar

@Composable
fun CreateChallengePreviewScreen(navController: NavController, _viewModel:CreateChallengePreviewViewModel = viewModel()) {
    val scrollState = rememberScrollState()

    val name: String by _viewModel.name.collectAsState("")
    val description: String by _viewModel.description.collectAsState("")
    val creator: String by _viewModel.creator.collectAsState("")
    val numClues: String by _viewModel.numClues.observeAsState("")
    val rating: Double by _viewModel.rating.observeAsState(0.0)
    val image by _viewModel.image.collectAsState()


    val challenge = navController.previousBackStackEntry?.savedStateHandle?.get<String>("name")
    LaunchedEffect(key1=challenge) {
        if (challenge != null) {
            _viewModel.saveBasicChallengeData(challenge)
        }
    }
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        Column(modifier = Modifier.padding(vertical = 15.dp, horizontal = 25.dp)) {
            LinearProgressIndicator(progress = 1f, modifier = Modifier.fillMaxWidth())
        }
        TitleImage(_viewModel)
        Column(
            modifier = Modifier.padding(vertical = 25.dp, horizontal = 25.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            Body(name = name, desc = description, creator= creator, rating = rating.toString())
            Body2(numClues = numClues.toString())
            Foot(navController,_viewModel)
        }
    }
}


@Composable
fun TitleImage(_viewModel:CreateChallengePreviewViewModel) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Image(
            contentScale = ContentScale.FillWidth,
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "_viewModel.image.value",
            modifier = Modifier.fillMaxWidth()
        )
    }
}
@Composable
fun Foot(navController: NavController,_viewModel:CreateChallengePreviewViewModel){
    Row(horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()){
        OutlinedButton(onClick = {navController.popBackStack() }) {
            Text(text = "Anterior")
        }
        Button(onClick = {// _viewModel.pushAvailableNotification()
            navController.navigate(BottomNavigationBar.MyChallenges.route) }) {
            Text(text = "Finalizar")

        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun Preview() {
    CreateChallengePreviewScreen(rememberNavController())
}