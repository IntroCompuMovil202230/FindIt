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
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ntn.findit.R
import com.ntn.findit.ui.screen.challengeinfo.Body
import com.ntn.findit.ui.screen.challengeinfo.Body2

@Composable
fun CreateChallengePreviewScreen() {
    val scrollState = rememberScrollState()
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        Column(modifier = Modifier.padding(vertical = 15.dp, horizontal = 25.dp)) {
            LinearProgressIndicator(progress = 1f, modifier = Modifier.fillMaxWidth())
        }
        TitleImage()
        Column(
            modifier = Modifier.padding(vertical = 25.dp, horizontal = 25.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            Body()
            Body2()
            Foot()
        }
    }
}


@Composable
fun TitleImage() {
    Box(modifier = Modifier.fillMaxWidth()) {
        Image(
            contentScale = ContentScale.FillWidth,
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "",
            modifier = Modifier.fillMaxWidth()
        )
    }
}
@Composable
fun Foot(){
    Row(horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()){
        OutlinedButton(onClick = { /*TODO*/ }) {
            Text(text = "Anterior")
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Finalizar")

        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun Preview() {
    CreateChallengePreviewScreen()
}