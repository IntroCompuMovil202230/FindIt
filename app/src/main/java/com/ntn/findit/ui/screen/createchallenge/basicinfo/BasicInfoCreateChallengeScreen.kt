package com.ntn.findit.ui.screen.createchallenge.basicinfo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ntn.findit.R
import com.ntn.findit.ui.screen.shared.CustomOutlinedTextField
import com.ntn.findit.ui.screen.shared.CustomSpacer

@Composable
fun BasicInfoCreateChallengeScreen(
    navController: NavController,
    _viewModel: BasicInfoCreateChallengeViewModel = viewModel()
) {
    Column(modifier = Modifier.padding(vertical = 45.dp, horizontal = 25.dp)) {
        LinearProgressIndicator(progress = 0.0f, modifier = Modifier.fillMaxWidth())
        CustomSpacer(5.0)
        Title()
        CustomSpacer(20.0)
        Body(_viewModel)
        Spacer(modifier = Modifier.weight(1f))
        Foot()
    }
}

@Composable
fun Title() {
    Column {
        Text(text = "Datos Básicos", fontWeight = FontWeight.ExtraBold, fontSize = 28.sp)
        CustomSpacer(10.0)
        Text(text = "Ingrese los datos básicos para crear el desafío")

    }
}

@Composable
fun Body(_viewModel: BasicInfoCreateChallengeViewModel) {
    val clueName: String by _viewModel.clueName.observeAsState("")
    val description: String by _viewModel.clueName.observeAsState("")
    Text(text = "Nombre del desafío", fontWeight = FontWeight.ExtraBold)
    CustomOutlinedTextField(
        hint = "Nombre",
        value = clueName,
        onTextChange = { _viewModel.onClueNameChange(it) })
    CustomSpacer(10.0)
    Text(text = "Descripción", fontWeight = FontWeight.ExtraBold)
    CustomOutlinedTextField(
        hint = "Breve Descripción",
        value = description,
        onTextChange = { _viewModel.onDescriptionChange(it) })
    CustomSpacer(10.0)
    Text(text = "Imágen", fontWeight = FontWeight.ExtraBold)
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_foreground),
        contentDescription = "",
        modifier = Modifier.fillMaxWidth(),
        contentScale = ContentScale.Fit,
    )
}

@Composable
fun Foot() {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedButton(onClick = { /*TODO*/ }) {
            Text(text = "Cancelar")
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Siguiente")
        }
    }
}


@Composable
@Preview(showSystemUi = true, name = "BasicInfoCreateChallengeScreen")
fun Preview() {
    BasicInfoCreateChallengeScreen(rememberNavController())
}