package com.ntn.findit.ui.screen.shared

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ntn.findit.ui.theme.FBlue
import com.ntn.findit.ui.theme.LightWhite


@Composable
fun CustomSpacer(padding: Double = 14.0) {
    Spacer(modifier = Modifier.padding(padding.dp))
}


@Composable
fun CustomButton(text: String, enabled: Boolean, onClick: () -> Unit) {
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
fun CustomOutlinedTextField(
    value: String,
    onTextChange: (String) -> Unit,
    hint: String,
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    OutlinedTextField(
        value = value,
        onValueChange = onTextChange,
        modifier = modifier,
        label = { Text(hint) },
        shape = RoundedCornerShape(20),
    )
}


@Composable
fun TitledTextField(title: String, hint: String, value: String, onTextChange: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(text = title, fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
        OutlinedTextField(
            value = value,
            onValueChange = onTextChange,
            label = { Text(hint) },
            shape = RoundedCornerShape(20),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun CustomPasswordOutlinedTextField(value: String, onTextChange: (String) -> Unit, hint: String) {
    var passwordVisible by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = value,
        onValueChange = onTextChange,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(hint) },
        shape = RoundedCornerShape(20),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val image = if (passwordVisible) Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff

            // Please provide localized description for accessibility services
            val description = if (passwordVisible) "Hide password" else "Show password"

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = image, description)
            }
        }
    )
}

@Composable
fun CustomClickableText(text: String, onClick: () -> Unit) {
    ClickableText(
        text = AnnotatedString(text, spanStyle = SpanStyle(color = FBlue)),
        onClick = { onClick() })

}

@Composable
fun SearchBarField() {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = { Icon(Icons.Default.Search, "") },
        shape = RoundedCornerShape(50),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = LightWhite, //TODO Change color
            focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent
        ),
    )
}

@Composable
fun SearchBarFieldFull() {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = { Icon(Icons.Default.Search, "") },
        shape = RoundedCornerShape(50),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = LightWhite,
            focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun CustomDivider() {
    Divider(color = Color.LightGray, thickness = 1.dp)
}
