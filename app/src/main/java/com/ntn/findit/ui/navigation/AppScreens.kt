package com.ntn.findit.ui.navigation

sealed class AppScreens(val route: String){
    object RegistrationScreen: AppScreens("registration")
    object LoginScreen: AppScreens("login")
    object MainScreen: AppScreens("home")
    object CreateChallenge: AppScreens("create_challenge")

}
