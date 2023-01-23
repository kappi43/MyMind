package com.example.hellofigma

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItems(
    val route: String,
    val name: String,
    val icon: ImageVector
){
    object Home : BottomNavItems("home", "Home", Icons.Rounded.Home)
    object Options : BottomNavItems("options", "Options", Icons.Rounded.Settings)
    object Stats : BottomNavItems("stats", "Stats", Icons.Rounded.Check)
}

val navItems = listOf(BottomNavItems.Home, BottomNavItems.Stats,  BottomNavItems.Options)