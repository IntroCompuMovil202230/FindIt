package com.ntn.findit.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ntn.findit.ui.screen.createchallenge.location.LocationCreateChallengeScreen
import com.ntn.findit.ui.screen.login.LoginScreen
import com.ntn.findit.ui.screen.registration.RegistrationScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.RegistrationScreen.route){
        composable(route = AppScreens.RegistrationScreen.route){
            LoginScreen()
        }
        composable(route = AppScreens.RegistrationScreen.route){
            LocationCreateChallengeScreen()
        }
    }
}