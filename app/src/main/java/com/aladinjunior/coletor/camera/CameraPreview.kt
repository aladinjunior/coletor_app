package com.aladinjunior.coletor.camera

import android.graphics.Color
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.graphics.Color as ComposeColor

@Composable
fun CameraPreview() {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val cameraController = remember { LifecycleCameraController(context) }



    Box(modifier = Modifier.size(350.dp).
    padding(16.dp)
        .shadow(8.dp, RoundedCornerShape(16.dp))
        .clip(RoundedCornerShape(16.dp))
        .background(ComposeColor.White)) {

        AndroidView(factory = { context ->

            PreviewView(context).apply {
                layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                setBackgroundColor(Color.BLACK)
                scaleType = PreviewView.ScaleType.FILL_START
            }.also { previewView ->
                previewView.controller = cameraController
                cameraController.bindToLifecycle(lifecycleOwner)
            }

        })
    }


}