package com.ntn.findit.ui.screen.mainscreen.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Mosque
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.ui.graphics.vector.ImageVector
import com.ntn.findit.R

sealed class BottomNavigationBar(var title:String, var icon:ImageVector, var screen_route:String){
    object Challenges: BottomNavigationBar("Desafíos", Icons.Default.Home, "challenges")
    object MyChallenges: BottomNavigationBar("Mis desafíos", Icons.Default.Mosque, "mychallenges")
    object Chat: BottomNavigationBar("Chat", Icons.Default.Chat, "chat")
    object Account: BottomNavigationBar("Cuenta", Icons.Rounded.AccountCircle, "account")
    object CreateChallenge: BottomNavigationBar("NAN", Icons.Rounded.AccountCircle, "create_challenge")
}
