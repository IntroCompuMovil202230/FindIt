package com.ntn.findit.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ntn.findit.ui.screen.createchallenge.location.LocationCreateChallengeScreen
import com.ntn.findit.ui.screen.login.LoginScreen
import com.ntn.findit.ui.screen.mainscreen.MainScreenView
import com.ntn.findit.ui.screen.mainscreen.innerscreen.challenge.ChallengeInnerScreen
import com.ntn.findit.ui.screen.registration.RegistrationScreen

@Composable
fun AppNavigation(navController: NavHostController){
    NavHost(navController = navController, startDestination = AppScreens.TestScreen.route){
        composable(route = AppScreens.RegistrationScreen.route){
            RegistrationScreen(navController = navController)
        }
        composable(route = AppScreens.LoginScreen.route){
            LoginScreen(navController = navController)
        }
        composable(route = AppScreens.MainScreen.route){
            MainScreenView(principalNavController = navController)
        }
        composable(route = AppScreens.TestScreen.route){
            //LocationCreateChallengeScreen(navController = navController)
            LoginScreen(navController = navController)
        }
        composable(route = AppScreens.BioAuthActivity.route){
            LoginScreen(navController = navController)
        }
    }
}