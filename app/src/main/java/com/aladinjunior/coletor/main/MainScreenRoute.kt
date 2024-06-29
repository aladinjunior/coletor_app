package com.aladinjunior.coletor.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.aladinjunior.coletor.camera.presentation.CameraViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aladinjunior.coletor.main.data.db.ScannedProduct

@Composable
fun MainScreenRoute(
    viewModel: CameraViewModel = viewModel(
        factory = CameraViewModel.CameraViewModelProvider.provideFactory(
            LocalContext.current
        )
    )
) {
    val isCollectionRunning by viewModel.isCollectRunning.collectAsState()
    MainScreen(
        mostRecentBarcode = {
            //TODO:
        },
        startCollect = {
            viewModel.startCollect()
        },
        isCollectionRunning = isCollectionRunning,
        onSaveBarcode = {
            viewModel.saveBarcode(ScannedProduct(barcode = "", quantity = 0, stockCode = ""))
        }
    )
}