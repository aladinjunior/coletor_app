package com.aladinjunior.coletor

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aladinjunior.coletor.TabOptions.tabs

object TabOptions {
    private const val SCAN_CODE = "Escanear Código"
    private const val HISTORY = "Histórico"

    val tabs = listOf(SCAN_CODE, HISTORY)

}


@Composable
fun MainScreen() {

    var selectedTabIndex by remember { mutableIntStateOf(0) }
    Scaffold(
        topBar = {
            TabRow(
                selectedTabIndex = selectedTabIndex,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                        color = Color.Blue


                    )
                }
            ) {
                tabs.forEachIndexed { index, tab ->
                    var interactionSource = remember { MutableInteractionSource() }

                    var isSelected = selectedTabIndex == index
                    Tab(
                        interactionSource = interactionSource,
                        selected = isSelected,
                        onClick = { selectedTabIndex = index },
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null
                            ) {},

                        ) {
                        Text(
                            text = tab,
                            color = if (isSelected) Color.Blue else Color.Blue.copy(
                                alpha = 0.4f
                            ),

                            )


                    }
                }
            }
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.Blue.copy(alpha = 0.4f)
            ) {

            }
        }
    ) {

        it.calculateBottomPadding()

    }


}


@Preview
@Composable
private fun MainScreenPreview() {

    MainScreen()


}