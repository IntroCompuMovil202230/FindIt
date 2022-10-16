package com.ntn.findit.ui.screen.mainscreen.innerscreen.mychallenge

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ntn.findit.R

@Composable
fun MyChallengeInnerScreen() {
    Column(
        modifier = Modifier.padding(vertical = 45.dp, horizontal = 25.dp),
        verticalArrangement = Arrangement.spacedBy(23.dp)
    ) {
        Titles()
        MyChallengesList()
    }
}

@Composable
fun Titles() {
    Row {
        SearchBar()
    }
}

@Composable
fun SearchBar() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        OutlinedTextField(
            value = "",
            onValueChange = {},
            leadingIcon = { Icon(Icons.Default.Search, "", tint = Color.Blue) },
            shape = RoundedCornerShape(30),
            modifier = Modifier.weight(1f)
        )
        OutlinedButton(
            onClick = { /*TODO*/ }, modifier = Modifier.weight(1f),
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "", tint = Color.Green)
                Text(text = "Crear desafío")
            }
        }
    }
}

@Composable
fun MyChallengesList() {
    LazyColumn {
        item {
            RowItem()
            Divider(color = Color.LightGray, thickness = 1.dp)
            Spacer(modifier = Modifier)
        }
    }
}

@Composable
fun RowItem() {
    Row (verticalAlignment = Alignment.CenterVertically){
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = ""
        )

        Column (Modifier.weight(1f)){
            Text(text = "Title")
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "5.0", fontSize = 18.sp)
                Icon(imageVector = Icons.Default.Star, contentDescription = "")
                Spacer(modifier = Modifier.weight(1f))
            }
            Row {
                Icon(imageVector = Icons.Default.AccountBox, contentDescription = "")
                Text(text = "12 pistas")
            }
        }
        Column (modifier = Modifier.weight(1f)){
            OutlinedButton(onClick = { /*TODO*/ }) {
                Text(text = "Jugar")
            }
            Row {
                Icon(imageVector = Icons.Default.AccountBox, contentDescription = "")
                Text(text = "12 pistas")
            }

        }
    }
}


@Composable
@Preview(showSystemUi = true)
fun Preview() {
    MyChallengeInnerScreen()
}