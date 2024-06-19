package com.aladinjunior.coletor.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.aladinjunior.coletor.camera.CameraPreview
import com.aladinjunior.coletor.camera.DefaultImageAnalyzer
import com.aladinjunior.coletor.camera.presentation.CameraViewModel
import com.google.mlkit.vision.barcode.common.Barcode

@Composable
fun ScanScreen(
    viewModel: CameraViewModel
) {
    val context = LocalContext.current
//    var barcodes by remember { mutableStateOf(listOf<Barcode>()) }
//    val analyzer = remember { DefaultImageAnalyzer(context) { detectedBarcodes ->
//        barcodes = detectedBarcodes
//    }
//    }

    Box(
        modifier = Modifier.fillMaxSize(),

    ) {
        CameraPreview()
//        BarcodeOverlay(barcodes = barcodes)

    }

}