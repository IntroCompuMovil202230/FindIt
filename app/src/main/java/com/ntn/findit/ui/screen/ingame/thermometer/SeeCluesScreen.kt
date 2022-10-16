package com.ntn.findit.ui.screen.ingame.thermometer

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ntn.findit.ui.screen.shared.CustomDivider
import com.ntn.findit.ui.screen.shared.CustomSpacer

@Composable
fun ShowCluesScreen() {
    Column(
        modifier = Modifier.padding(vertical = 45.dp, horizontal = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CluesTitle()
        CustomSpacer(10.0)
        CustomDivider()
        CustomSpacer(10.0)
        CluesList()
    }

}


@Composable
fun CluesTitle() {
    Text(text = "Pistas del desafío", fontWeight = FontWeight.ExtraBold, fontSize = 30.sp)
}

@Composable
fun CluesList() {
    val scrollState = rememberScrollState()
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        CluesItem()
        CluesItem()
        CluesItem()
        CluesItem()
        CluesItem()
        CluesItem()
        CluesItem()


    }
}

@Composable
fun CluesItem() {
    Card(
        shape = RoundedCornerShape(10.dp),
    ) {
        Text(
            "Pista Principal",
            fontSize = 20.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp)
        )
    }
}

@Composable
@Preview(showSystemUi = true)
fun PreviewClues() {
    ShowCluesScreen()
}