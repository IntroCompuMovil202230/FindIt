package com.ntn.findit.ui.screen.createchallenge.createclues

import android.icu.text.CaseMap.Title
import android.util.Log
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ntn.findit.model.Challenge
import com.ntn.findit.model.Clue
import com.ntn.findit.ui.screen.createchallenge.CreateChallenge
import com.ntn.findit.ui.screen.createchallenge.basicinfo.BasicInfoCreateChallengeViewModel
import com.ntn.findit.ui.screen.shared.CustomSpacer
import kotlin.math.round

@Composable
fun CluesCreateChallengeScreen(navController: NavController,_viewModel: ClueCreateChallengeViewModel = viewModel()) {
    Column(Modifier.padding(vertical = 45.dp, horizontal = 25.dp)) {
        LinearProgressIndicator(progress = 0.7f, modifier = Modifier.fillMaxWidth())
        CustomSpacer(2.0)
        Title()
        CustomSpacer(5.0)
        ListHeaders(navController)
        CustomSpacer(5.0)
        ListClues()
        Foot()

        val challenge =
            navController.previousBackStackEntry?.savedStateHandle?.get<String>("name")
        LaunchedEffect(key1=challenge) {
            if (challenge != null) {
                Log.i("challenge status", challenge)
                _viewModel.getClues(challenge)
                navController.currentBackStackEntry?.savedStateHandle?.set(key="name",value=challenge)
            }
        }


    }
}

@Composable
fun Title() {
    Text(text = "Agregar Pistas", fontWeight = FontWeight.ExtraBold, fontSize = 30.sp)
    CustomSpacer(5.0)
    Text(text = "Añade pistas para completar tu desafío")

}

@Composable
fun ListHeaders(navController: NavController) {
    Row {
        Spacer(modifier = Modifier.weight(1f))
        OutlinedButton(
            onClick = { navController.navigate(CreateChallenge.AddClue.route) },
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "", tint = Color.Green)
                Text(text = "Añadir pista")
            }
        }
    }
}

@Composable
fun ListClues(_viewModel: ClueCreateChallengeViewModel = viewModel()) {
    val scrollState = rememberScrollState()
    val clues by _viewModel.clues.collectAsState()
    LazyColumn(
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        itemsIndexed(clues) { _, obj ->
            run {
                ItemClue(clue = obj)
            }
        }
    }
}

@Composable
fun ItemClue(clue: Clue) {
    Card(border = BorderStroke(1.dp, Color.LightGray), shape = RoundedCornerShape(20)) {
        Row(modifier = Modifier.padding(20.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(text = clue.name)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = clue.type)
            CustomSpacer(12.0)
            Icon(imageVector = Icons.Default.Edit, contentDescription = "")
        }
    }
}


@Composable
fun Foot() {
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Spacer(modifier = Modifier.weight(1f))
        OutlinedButton(onClick = { /*TODO*/ }) {
            Text(text = "Anterior")
        }
        CustomSpacer(10.0)
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Siguiente")
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
@Preview(showSystemUi = true)
fun Preview() {
    CluesCreateChallengeScreen(rememberNavController())
}