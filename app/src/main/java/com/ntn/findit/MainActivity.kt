package com.ntn.findit

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.ntn.findit.ui.navigation.AppNavigation
import com.ntn.findit.ui.navigation.AppScreens
import com.ntn.findit.ui.theme.FindItTheme
import com.parse.ParseUser




class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val currentUser = ParseUser.getCurrentUser()
        if (currentUser != null){
            Log.d("Mio", "${currentUser.username}, ---- ${currentUser.email}")

        }
        val destination = if(currentUser == null){
            AppScreens.LoginScreen
        }else{
            AppScreens.MainScreen
        }
        setContent {
            val navController = rememberNavController()
            FindItTheme{
                AppNavigation(navController, destination)
            }
        }
    }
}