package com.aladinjunior.coletor

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aladinjunior.coletor.bottom_nav.BottomNavItem
import com.aladinjunior.coletor.main.MainScreenRoute
import com.aladinjunior.coletor.register.RegisterCodeRoute


@Composable
fun ColetorNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    NavHost(modifier = modifier, navController = navController, startDestination = BottomNavItem.Home.route) {
        composable(BottomNavItem.Home.route) {
            MainScreenRoute()
        }
        composable(BottomNavItem.Register.route){
            RegisterCodeRoute()
        }
    }

}