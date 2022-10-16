package com.ntn.findit.ui.navigation

sealed class AppScreens(val route: String){
    object RegistrationScreen: AppScreens("registration_screen")
    object TestScreen: AppScreens("test")
}
