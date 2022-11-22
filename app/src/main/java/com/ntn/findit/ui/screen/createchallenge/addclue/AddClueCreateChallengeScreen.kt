package com.ntn.findit.ui.screen.createchallenge.addclue

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import coil.compose.AsyncImage
import com.ntn.findit.model.Challenge
import com.ntn.findit.ui.screen.shared.CustomDivider
import com.ntn.findit.ui.screen.shared.CustomSpacer
import com.ntn.findit.ui.screen.shared.TitledTextField

@Composable
fun AddClueCreateChallengeScreen(navController: NavController) {
    Body(navController = navController)
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Body(_viewModel: AddClueViewModel = viewModel(),navController: NavController) {
    val clueName: String by _viewModel.clueName.observeAsState("")
    val question: String by _viewModel.question.collectAsState("")
    val answer: String by _viewModel.answer.collectAsState("")
    val content: String by _viewModel.content.collectAsState("")
    val points: Int by _viewModel.points.observeAsState(0)
    val expanded by _viewModel.expanded.collectAsState()
    val image by _viewModel.image.collectAsState()

    val selectedOptionText by _viewModel.selectedTypeText.collectAsState()
    val options = listOf(
        "Pregunta",
        "Imagen",
        "Texto"
    )
    val galleryLauncher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            _viewModel.setImage(uri)
        }
    }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .padding(vertical = 45.dp, horizontal = 25.dp)
            .verticalScroll(scrollState)
        ) {

            Title()
            Subtitle()
            Text(text = "Nombre de la pista", fontWeight = FontWeight.ExtraBold)
            OutlinedTextField(
                value = clueName,
                onValueChange = { _viewModel.onClueNameChange(it) },
                label = { Text("Nombre de la pista") },
                shape = RoundedCornerShape(20),
                modifier = Modifier.fillMaxWidth()
            )

            CustomDivider()
            /*
        // TODO IMPLEMENT
        Text(text = "Contenido de la pista", fontWeight = FontWeight.ExtraBold)
        OutlinedTextField(
            value = clueContent,
            onValueChange = {_viewModel.onContentChange(it)},
            label = { Text("Pista...") },
            shape = RoundedCornerShape(20),
            modifier = Modifier.fillMaxWidth()
        )

         */
            //   CustomDivider()
            /*
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

         */
            ExposedDropdownMenuBox(
                modifier = Modifier.fillMaxWidth(),
                expanded = expanded,
                onExpandedChange = {
                    _viewModel.setExpanded(!expanded)
                }
            ) {
                TextField(
                    readOnly = true,
                    value = selectedOptionText,
                    onValueChange = { _viewModel.setSelectedTypeText(it) },
                    label = { Text("Tipo de Pista") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expanded
                        )
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors()
                )
                ExposedDropdownMenu(
                    modifier = Modifier.fillMaxWidth(),
                    expanded = expanded,
                    onDismissRequest = {
                        _viewModel.setExpanded(false)
                    }
                ) {
                    options.forEach { selectionOption ->
                        DropdownMenuItem(
                            onClick = {
                                _viewModel.setSelectedTypeText(selectionOption)
                                _viewModel.setExpanded(false)
                            }
                        ) {
                            Text(text = selectionOption)
                        }
                    }
                }
            }

            CustomDivider()
            Spacer(modifier = Modifier.height(12.dp))

            if (selectedOptionText.equals("Pregunta")) {
                TitledTextField(title = "Pregunta", hint = "Pregunta", value = question) {
                    _viewModel.setQuestion(
                        it
                    )
                }
                TitledTextField(title = "Respuesta", hint = "Respuesta", value = answer) {
                    _viewModel.setAnswer(
                        it
                    )
                }
            }
            if (selectedOptionText.equals("Imagen")) {
                Column {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(onClick = { galleryLauncher.launch("clues/*") })
                        { Text("Buscar en la galería") }

                        AsyncImage(model = image, contentDescription = "")
                    }
                }
            }
                if (selectedOptionText.equals("Texto")) {
                    TitledTextField(title = "Texto", hint = "Texto", value = content) {
                        _viewModel.setContent(
                            it
                        )
                    }
                }

                //   CustomDivider()
                Spacer(modifier = Modifier.weight(1f))
                Button(onClick = {
                    val challenge = navController.previousBackStackEntry?.savedStateHandle?.get<String>("name")
                    if (challenge != null) {
                        Log.i("esto llega", challenge)
                    }
                    if (_viewModel.createEnabled.value == true) {
                        if (challenge != null && !challenge.isEmpty()) {
                            Log.i("si llega", challenge)
                            _viewModel.save(challenge)
                        }
                    }
                    navController.popBackStack()

                }, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Crear Pista")
                }
            }
        }




@Preview(showSystemUi = true)
@Composable
fun Preview() {
    AddClueCreateChallengeScreen(rememberNavController())
}