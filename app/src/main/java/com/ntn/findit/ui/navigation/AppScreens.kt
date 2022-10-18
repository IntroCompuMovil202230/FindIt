package com.ntn.findit.ui.navigation

sealed class AppScreens(val route: String){
    object RegistrationScreen: AppScreens("registration")
    object LoginScreen: AppScreens("login")
    object MainScreen: AppScreens("home")
    object HomeScreen: AppScreens("home")
    object TestScreen: AppScreens("test")
    object BioAuthActivity: AppScreens("bio")
}
