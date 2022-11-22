package com.ntn.findit.ui.screen.createchallenge.basicinfo

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.elevation
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.ntn.findit.R
import com.ntn.findit.logic.fileprovider.ComposeFileProvider
import com.ntn.findit.model.Challenge
import com.ntn.findit.ui.screen.createchallenge.CreateChallenge
import com.ntn.findit.ui.screen.createchallenge.SharedViewModel
import com.ntn.findit.ui.screen.shared.CustomOutlinedTextField
import com.ntn.findit.ui.screen.shared.CustomSpacer
import com.parse.ParseUser
import kotlinx.coroutines.launch

@Composable
fun BasicInfoCreateChallengeScreen(
    navController: NavController,
    _viewModel: BasicInfoCreateChallengeViewModel = viewModel(),
    _sharedViewModel: SharedViewModel = viewModel()
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .padding(vertical = 45.dp, horizontal = 25.dp)
            .verticalScroll(scrollState)
    ) {
        LinearProgressIndicator(progress = 0.0f, modifier = Modifier.fillMaxWidth())
        CustomSpacer(5.0)
        Title()
        CustomSpacer(20.0)
        Body()
        Spacer(modifier = Modifier.weight(1f))
        Foot(navController)
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
fun Body(_viewModel: BasicInfoCreateChallengeViewModel = viewModel(),_sharedViewModel: SharedViewModel = viewModel()) {
    var hasImage by remember {
        mutableStateOf(false)
    }
    val clueName: String by _viewModel.clueName.observeAsState("")
    val description: String by _viewModel.description.observeAsState("")
    val imageUri by _viewModel.imageUri.observeAsState(null)
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            hasImage = success
        }
    )
    val context = LocalContext.current



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
    Text(text = "Imagen", fontWeight = FontWeight.ExtraBold)
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            onClick = {
                val uri = ComposeFileProvider.getImageUri(context)
                cameraLauncher.launch(uri)
                _viewModel.onUriChange(uri)
                _viewModel.uploadImage(uri)
            },
            modifier = Modifier.clip(CircleShape),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
            elevation = elevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp
            )
        ) {
            val painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(data = imageUri)
                    .apply(block = fun ImageRequest.Builder.() {
                        placeholder(R.drawable.ic_launcher_background)
                        transformations(CircleCropTransformation())
                    }).build()
            )
            val painterState = painter.state
            Image(
                painter = painter,
                contentDescription = ""
            )
        }
    }
}

@Composable
fun Foot(
    navController: NavController,
    _viewModel: BasicInfoCreateChallengeViewModel = viewModel(),
    _sharedViewModel: SharedViewModel = viewModel()
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedButton(onClick = { /*TODO*/ }) {
            Text(text = "Cancelar")
        }
        val enabled by _viewModel.continueEnable.observeAsState(false)
        Button(
            onClick = {
                var c: Challenge = Challenge()
                c.name=_viewModel.clueName.value.toString()
                c.description = _viewModel.description.value.toString()
                c.imageUrl = "challenges/"+_viewModel.clueName.value.toString()+".jpg"
                c.creator = ParseUser.getCurrentUser().username
                _sharedViewModel.setChallen(c)
             navController.currentBackStackEntry?.savedStateHandle?.set(key="challenge",value=c)
                navController.navigate(CreateChallenge.ChallengeLocation.route) },
            enabled = enabled
        ) {
            Text(text = "Siguiente")
        }
    }
}


@Composable
@Preview(showSystemUi = true, name = "BasicInfoCreateChallengeScreen")
fun Preview() {
    BasicInfoCreateChallengeScreen(rememberNavController())
}

