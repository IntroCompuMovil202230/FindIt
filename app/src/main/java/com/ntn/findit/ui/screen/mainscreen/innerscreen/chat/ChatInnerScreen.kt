package com.ntn.findit.ui.screen.mainscreen.innerscreen.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ntn.findit.R
import com.ntn.findit.ui.screen.shared.CustomSpacer
import com.ntn.findit.ui.screen.shared.SearchBarFieldFull

@Composable
fun ChatInnerScreen() {
    Column(Modifier.padding(vertical = 45.dp, horizontal = 25.dp)) {
        SearchBarFieldFull()
        CustomSpacer(15.0)
        ChatList()
    }
}

@Composable
fun ChatList() {
    LazyColumn {
        item {
            ChatItem()
            ChatItem()
            ChatItem()
            ChatItem()

        }
    }
}

@Composable
fun ChatItem() {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "",
            modifier = Modifier
                .clip(CircleShape)
                .size(80.dp)
        )
        Column (Modifier.weight(11f)){
            Text(text = "Haley Jones", fontWeight = FontWeight.ExtraBold)
            Text(text = "Haley Jones", fontWeight = FontWeight.Light)
        }
        Text(text = "1", modifier = Modifier
            .clip(CircleShape)
            .background(Color.Blue)
            .weight(1f),
            textAlign = TextAlign.Center,
            color = Color.White
        )
    }
}

@Composable
@Preview(showSystemUi = true)
fun Preview() {
    ChatInnerScreen()
}