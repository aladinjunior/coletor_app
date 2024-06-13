package com.aladinjunior.coletor

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = BottomNavItem.Home.route,
        enterTransition = { EnterTransition.None},
        exitTransition = { ExitTransition.None} ) {
        composable(BottomNavItem.Home.route) {
            MainScreenRoute()
        }
        composable(BottomNavItem.Register.route,
            enterTransition = {
                fadeIn(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                ) + slideIntoContainer(
                    animationSpec = tween(300, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                ) + slideOutOfContainer(
                    animationSpec = tween(300, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            }){
            RegisterCodeRoute()
        }
    }

}