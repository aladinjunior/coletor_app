package com.aladinjunior.coletor.bottom_nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val label: String, val icon: ImageVector) {
    data object Home : BottomNavItem("home", "Início", Icons.Default.Home)
    data object Register : BottomNavItem("register", "Registrar", Icons.Default.AddCircle)
}