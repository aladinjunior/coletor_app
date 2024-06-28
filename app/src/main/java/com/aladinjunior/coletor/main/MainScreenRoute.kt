package com.aladinjunior.coletor.main

import android.util.Log
import androidx.compose.runtime.Composable
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
    MainScreen {
        val TAG = "MainScreenRoute_Coletor"
        Log.d(TAG, "MainScreenRoute: $it")
    }
}