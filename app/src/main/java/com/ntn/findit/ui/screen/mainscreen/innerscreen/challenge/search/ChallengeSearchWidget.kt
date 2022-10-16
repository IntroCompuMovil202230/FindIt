package com.ntn.findit.ui.screen.mainscreen.innerscreen.challenge.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ntn.findit.R
import com.ntn.findit.ui.screen.shared.CustomDivider
import com.ntn.findit.ui.screen.shared.CustomSpacer


@Composable
fun SearchListWidget() {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        item {
            ChallengeSearchItem()
            ChallengeSearchItem()
            ChallengeSearchItem()
        }
    }
}

@Composable
fun ChallengeSearchItem() {
    CustomDivider()
    CustomSpacer(10.0)
    Row(
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "",
            modifier = Modifier.clip(RoundedCornerShape(10.dp))
        )
        Column {
            Text("Nombre", fontWeight = FontWeight.ExtraBold)
            Row {
                Text(text = "5.0", fontSize = 18.sp)
                Icon(imageVector = Icons.Default.Star, contentDescription = "")
            }
            Row {
                Icon(imageVector = Icons.Default.Info, contentDescription = "")
                CustomSpacer(5.0)
                Text(text = "5 pistas")
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        OutlinedButton(onClick = { /*TODO*/ }) {
            Text(text = "Jugar")
        }
    }
    CustomSpacer(10.0)
}

@Preview(showSystemUi = true)
@Composable
fun Preview() {
    SearchListWidget()
}