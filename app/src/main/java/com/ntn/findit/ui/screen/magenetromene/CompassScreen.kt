package com.ntn.findit.ui.screen.magenetromene

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ntn.findit.ui.screen.login.LoginViewModel
import com.ntn.findit.ui.screen.magenetromene.ui.theme.FindItTheme

@Composable
fun CompassScreen(x: LiveData<String>, y: LiveData<String>, z: LiveData<String>) {
    val x: String by x.observeAsState("")
    val y: String by y.observeAsState("")
    val z: String by z.observeAsState("")

    Text(text = "Sensor values x=$x y=$y z=$z")
}
