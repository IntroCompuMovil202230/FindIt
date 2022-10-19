package com.ntn.findit.ui.screen.createchallenge.addclue

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ntn.findit.ui.screen.shared.CustomDivider
import com.ntn.findit.ui.screen.shared.CustomSpacer

@Composable
fun AddClueCreateChallengeScreen(navController: NavController) {
    Column(
        Modifier.padding(vertical = 45.dp, horizontal = 25.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Title()
        Subtitle()
        Body()
    }
}

@Composable
fun Title() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Añadir Pista", fontWeight = FontWeight.ExtraBold, fontSize = 25.sp)
        CustomDivider()
    }
}

@Composable
fun Subtitle() {
    Column {
        Text(text = "Nueva Pista", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
        CustomSpacer(5.0)
        Text(text = "Ingrese los datos para crear una nueva pista")
    }
}

@Composable
fun Body(_viewModel: AddClueViewModel = viewModel()) {
    val clueName: String by _viewModel.clueName.observeAsState("")
    val clueContent: String by _viewModel.clueContent.observeAsState("")
    val points: Int by _viewModel.points.observeAsState(0)
    Column(verticalArrangement = Arrangement.spacedBy(15.dp)) {
        Text(text = "Nombre de la pista", fontWeight = FontWeight.ExtraBold)
        OutlinedTextField(
            value = clueName,
            onValueChange = {_viewModel.onClueNameChange(it)},
            label = { Text("Nombre de la pista") },
            shape = RoundedCornerShape(20),
            modifier = Modifier.fillMaxWidth()
        )

        CustomDivider()
        // TODO IMPLEMENT
        Text(text = "Contenido de la pista", fontWeight = FontWeight.ExtraBold)
        OutlinedTextField(
            value = clueContent,
            onValueChange = {_viewModel.onContentChange(it)},
            label = { Text("Pista...") },
            shape = RoundedCornerShape(20),
            modifier = Modifier.fillMaxWidth()
        )
        CustomDivider()
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Puntaje de penalización", modifier = Modifier.padding(horizontal = 5.dp))
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { _viewModel.onModifyPoints(1) }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
            Text(text = points.toString(), fontSize = 14.sp)
            IconButton(onClick = {_viewModel.onModifyPoints(-1)}) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "")
            }
        }
        CustomDivider()
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Crear Pista")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun Preview() {
    AddClueCreateChallengeScreen(rememberNavController())
}