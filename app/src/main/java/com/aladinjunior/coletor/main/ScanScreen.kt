package com.aladinjunior.coletor.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.aladinjunior.coletor.camera.CameraPreview
import com.aladinjunior.coletor.camera.presentation.CameraViewModel

@Composable
fun ScanScreen(
    viewModel: CameraViewModel
) {


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CameraPreview()

    }

}