package com.ntn.findit.ui.screen.ingame.thermometer

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

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
fun WinConfirmation() {
    AlertDialog(
        onDismissRequest = { /*TODO*/ },
        title = { Text(text = "Partida Ganada", fontWeight = FontWeight.ExtraBold) },
        text = { Text(text = "Has ganado la partida, ¡disfruta de tu destino!")},
        confirmButton = { Button(onClick = { /*TODO*/ }) {
            Text(text = "Continuar")
        }}
    )
}


@Preview
@Composable
fun PreviewDialog(){
    WinConfirmation()
}

@Composable
@Preview
fun PreviewDialog2(){
    EndConfirmation()
}