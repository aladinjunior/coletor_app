package com.aladinjunior.coletor.main


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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

object TabOptions {
    private const val SCAN_CODE = "Escanear Código"
    private const val HISTORY = "Histórico"

    val tabs = listOf(SCAN_CODE, HISTORY)

}


@Composable
fun MainScreen(
    mostRecentBarcode: (String?) -> Unit,
    startCollect: () -> Unit,
    isCollectionRunning: Boolean,
    onSaveBarcode: () -> Unit,
    quantityFieldText: String,
    onQuantityFieldValueChange: (String) -> Unit,

) {
    Column {
        AppTabRow(
            mostRecentBarcode = mostRecentBarcode,
            startCollect = startCollect,
            isCollectionRunning = isCollectionRunning,
            onSaveBarcode = onSaveBarcode,
            quantityFieldText = quantityFieldText,
            onQuantityFieldValueChange = onQuantityFieldValueChange
        )
    }
}


@Composable
fun AppTabRow(
    mostRecentBarcode: (String?) -> Unit,
    startCollect: () -> Unit,
    isCollectionRunning: Boolean,
    onSaveBarcode: () -> Unit,
    quantityFieldText: String,
    onQuantityFieldValueChange: (String) -> Unit,
    tabsContent: List<@Composable () -> Unit> = listOf(
        {
            ScanScreen(
                mostRecentBarcode,
                startCollect = startCollect,
                isCollectionRunning = isCollectionRunning,
                onSaveBarcode = onSaveBarcode,
                quantityFieldText = quantityFieldText,
                onQuantityFieldValueChange = onQuantityFieldValueChange
            )
        },
        { HistoryScreen() }
    )
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Column {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    color = Color.Blue


                )
            }
        ) {
            TabOptions.tabs.forEachIndexed { index, tab ->
                val isSelected = selectedTabIndex == index
                Tab(
                    selected = isSelected,
                    onClick = {
                        selectedTabIndex = index
                    },
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(
                        text = tab,
                        color = if (isSelected) Color.Blue else Color.Blue.copy(alpha = 0.4f),
                    )


                }
            }
        }
        tabsContent.getOrNull(selectedTabIndex)?.invoke()
    }

}

