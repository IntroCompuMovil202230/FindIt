package com.ntn.findit.ui.screen.mainscreen.navigation

import com.ntn.findit.R

sealed class BottomNavigationBar(var title:String, var icon:Int, var screen_route:String){
    object Challenges: BottomNavigationBar("Desafíos", R.drawable.ic_launcher_foreground, "challenges")
    object MyChallenges: BottomNavigationBar("Mis desafíos", R.drawable.ic_launcher_foreground, "mychallenges")
    object Chat: BottomNavigationBar("Chat", R.drawable.ic_launcher_foreground, "chat")
    object Account: BottomNavigationBar("Cuenta", R.drawable.ic_launcher_foreground, "account")
}
