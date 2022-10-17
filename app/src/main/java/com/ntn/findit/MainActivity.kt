package com.ntn.findit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.ntn.findit.ui.navigation.AppNavigation
import com.ntn.findit.ui.theme.FindItTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            FindItTheme{
                AppNavigation(navController)
            }
        }
    }
}