package com.aladinjunior.coletor

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.aladinjunior.coletor.bottom_nav.BottomNavBar



@Composable
fun ColetorApp() {

    val navController: NavHostController = rememberNavController()
    Scaffold(
        topBar = {

        },
        bottomBar = {
            BottomNavBar(navController = navController)

        }
    ) { paddingValues ->
        ColetorNavHost(
            navController = navController,
            modifier = Modifier.padding(paddingValues)
        )

    }

}