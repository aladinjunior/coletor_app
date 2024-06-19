package com.aladinjunior.coletor.camera

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import com.google.mlkit.vision.barcode.common.Barcode

//@Composable
//fun BarcodeOverlay(
//    barcodes: List<Barcode>
//) {
//    Canvas(modifier = Modifier.fillMaxSize()) {
//        barcodes.forEach { barcode ->
//            val rect = barcode.boundingBox
//            if (rect != null) {
//                drawRect(
//                    color = Color.Red,
//                    topLeft = Offset(rect.left.toFloat(), rect.top.toFloat()),
//                    size = Size(rect.width().toFloat(), rect.height().toFloat()),
//                    style = Stroke(width = 4f)
//                )
//            }
//        }
//    }
//}

@Composable
fun BarcodeOverlay(barcodes: List<Barcode>) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        barcodes.forEach { barcode ->
            barcode.boundingBox?.let { boundingBox ->
                drawRect(
                    color = Color.Yellow,
                    topLeft = Offset(boundingBox.left.toFloat(), boundingBox.top.toFloat()),
                    size = Size(boundingBox.width().toFloat(), boundingBox.height().toFloat()),
                    style = Stroke(width = 8f)
                )
            }
        }
    }
}
