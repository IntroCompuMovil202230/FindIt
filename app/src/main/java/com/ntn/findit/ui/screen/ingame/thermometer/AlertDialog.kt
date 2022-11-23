package com.ntn.findit.ui.screen.ingame.thermometer

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ntn.findit.ui.navigation.Graph

@Composable
fun EndConfirmation() {
    AlertDialog(
        onDismissRequest = { /*TODO*/ },
        title = { Text(text = "Partida en curso", fontWeight = FontWeight.ExtraBold) },
        text = { Text(text = "¿Deseas salir de la partida actual? Perderas todo tu progreso")},
        confirmButton = { Button(onClick = { /*TODO*/ }) {
            Text(text = "Salir")
        }},
        dismissButton = { OutlinedButton(onClick = { /*TODO*/ }) {
            Text(text = "Cancelar")
        }}
    )
}
@Composable
fun WinConfirmation(navController: NavController) {
    AlertDialog(
        onDismissRequest = { navController.navigate(Graph.HOME) },
        title = { Text(text = "Partida Ganada", fontWeight = FontWeight.ExtraBold) },
        text = { Text(text = "Has ganado la partida, ¡disfruta de tu destino!")},
        confirmButton = { Button(onClick = {navController.navigate(Graph.HOME) }) {
            Text(text = "Continuar")
        }}
    )
}


@Preview
@Composable
fun PreviewDialog(){
    WinConfirmation(rememberNavController())
}

@Composable
@Preview
fun PreviewDialog2(){
    EndConfirmation()
}