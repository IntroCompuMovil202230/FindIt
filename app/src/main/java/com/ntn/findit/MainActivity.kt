package com.ntn.findit

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.ntn.findit.ui.navigation.AuthScreen
import com.ntn.findit.ui.navigation.Graph
import com.ntn.findit.ui.navigation.RootNavigationGraph
import com.ntn.findit.ui.screen.mainscreen.navigation.BottomNavigationBar
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
            Graph.AUTHENTICATION
        }else{
            Graph.HOME
        }
        setContent {
            val navController = rememberNavController()
            FindItTheme{
                RootNavigationGraph(navController = navController, destination)
            }
        }
    }
}