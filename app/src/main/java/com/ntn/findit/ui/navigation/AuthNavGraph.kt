package com.ntn.findit.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ntn.findit.ui.screen.login.LoginScreen
import com.ntn.findit.ui.screen.registration.RegistrationScreen

fun NavGraphBuilder.authNavGraph(navController: NavController){
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ){
        composable(route = AuthScreen.Login.route){
            LoginScreen(navController = navController)
        }
        composable(route = AuthScreen.SignUp.route){
            RegistrationScreen(navController = navController)
        }
    }
}

sealed class AuthScreen(val route: String){
    object Login : AuthScreen("login_screen")
    object SignUp: AuthScreen("signup_screen")
}