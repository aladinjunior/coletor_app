package com.aladinjunior.coletor.camera

import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.google.mlkit.vision.barcode.common.Barcode


@Composable
fun CameraPreview() {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val cameraController = remember { LifecycleCameraController(context) }
    var barcodes by remember { mutableStateOf(emptyList<Barcode>()) }


    val previewView = remember {
        PreviewView(context).apply {
            layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            scaleType = PreviewView.ScaleType.FILL_START
        }.also { previewView ->
            previewView.controller = cameraController
        }
    }



    val defaultImageAnalyzer = remember {
        DefaultImageAnalyzer(context) { detectedBarcodes ->
            barcodes = detectedBarcodes
        }
    }


    LaunchedEffect(cameraController, lifecycleOwner) {
        cameraController.bindToLifecycle(lifecycleOwner)

    }


    cameraController.setImageAnalysisAnalyzer(
        ContextCompat.getMainExecutor(context),
        defaultImageAnalyzer.mlKitAnalyzer
    )

    Box(
        modifier = Modifier
            .size(350.dp)
            .padding(16.dp)
            .shadow(8.dp, RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
    ) {
        AndroidView(factory = { previewView })
        BarcodeOverlay(barcodes = barcodes)
    }


}

