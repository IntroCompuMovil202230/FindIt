package com.ntn.findit.ui.screen.mainscreen.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Mosque
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationBar(var title:String, var icon:ImageVector, var route:String){
    object Challenges: BottomNavigationBar("Desafíos", Icons.Default.Home, "challenges")
    object MyChallenges: BottomNavigationBar("Mis desafíos", Icons.Default.Mosque, "mychallenges")
    object Chat: BottomNavigationBar("Chat", Icons.Default.Chat, "chat")
    object Account: BottomNavigationBar("Cuenta", Icons.Rounded.AccountCircle, "account")
}
