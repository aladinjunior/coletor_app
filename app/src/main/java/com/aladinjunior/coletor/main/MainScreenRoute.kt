package com.aladinjunior.coletor.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.aladinjunior.coletor.camera.presentation.CameraViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

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
            //TODO: Vai tomar no cu
        },
        startCollect = {
            viewModel.startCollect()
        },
        isCollectionRunning = isCollectionRunning
    )
}