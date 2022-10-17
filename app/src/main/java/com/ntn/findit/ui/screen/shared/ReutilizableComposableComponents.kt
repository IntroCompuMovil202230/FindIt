package com.ntn.findit.ui.screen.shared

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp


@Composable
fun CustomSpacer(padding: Double = 14.0){
    Spacer(modifier = Modifier.padding(padding.dp))
}


@Composable
fun CustomButton(text: String, enabled: Boolean, onClick: () -> Unit){
    Button(
        onClick = { onClick() },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(40),
        enabled = enabled
    ) {
        Text(text = text)
    }
}

@Composable
fun CustomOutlinedTextField(value: String, onTextChange: (String) -> Unit,hint: String, modifier: Modifier = Modifier.fillMaxWidth()){
    OutlinedTextField(value = value, onValueChange = onTextChange, modifier = modifier, label = { Text(hint) }, shape = RoundedCornerShape(20))
}

@Composable
fun CustomPasswordOutlinedTextField(value:String, onTextChange: (String) -> Unit,hint: String){
    OutlinedTextField(value = value, onValueChange = onTextChange, modifier = Modifier.fillMaxWidth(),
        label = { Text(hint) },
        shape = RoundedCornerShape(20),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}
@Composable
fun CustomClickableText(text: String, onClick: () -> Unit){
    ClickableText(text = AnnotatedString(text), onClick = { onClick() })

}

@Composable
fun SearchBarField(){
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = { Icon(Icons.Default.Search, "") },
        shape = RoundedCornerShape(50),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.LightGray, //TODO Change color
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
    )
}

@Composable
fun SearchBarFieldFull(){
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = { Icon(Icons.Default.Search, "") },
        shape = RoundedCornerShape(50),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.LightGray, //TODO Change color
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun CustomDivider(){
    Divider(color = Color.LightGray, thickness = 1.dp)
}
