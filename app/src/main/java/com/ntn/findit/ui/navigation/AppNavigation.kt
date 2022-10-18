package com.ntn.findit.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ntn.findit.ui.screen.createchallenge.CreateChallenge
import com.ntn.findit.ui.screen.createchallenge.basicinfo.BasicInfoCreateChallengeScreen
import com.ntn.findit.ui.screen.createchallenge.location.LocationCreateChallengeScreen
import com.ntn.findit.ui.screen.login.LoginScreen
import com.ntn.findit.ui.screen.mainscreen.MainScreenView
import com.ntn.findit.ui.screen.registration.RegistrationScreen

@Composable
fun AppNavigation(navController: NavHostController, destination: AppScreens){
    NavHost(navController = navController, startDestination = destination.route){
        composable(route = AppScreens.RegistrationScreen.route){
            RegistrationScreen(navController = navController)
        }
        composable(route = AppScreens.LoginScreen.route){
            LoginScreen(navController = navController)
        }
        composable(route = AppScreens.MainScreen.route){
            MainScreenView(principalNavController = navController)
        }
        composable(route = AppScreens.CreateChallenge.route){
            CreateChallenge()
        }
    }
}