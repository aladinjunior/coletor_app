package com.aladinjunior.coletor.camera

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import com.google.mlkit.vision.barcode.common.Barcode


@Composable
fun BarcodeOverlay(barcodes: List<Barcode>) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        barcodes.forEach { barcode ->
            barcode.boundingBox?.let { boundingBox ->
                val text = barcode.rawValue ?: "Unknown"
                drawRoundRect(
                    color = Color.Yellow,
                    topLeft = Offset(boundingBox.left.toFloat(), boundingBox.top.toFloat()),
                    size = Size(boundingBox.width().toFloat(), boundingBox.height().toFloat()),
                    cornerRadius = CornerRadius(x = 16f, y = 16f),
                    style = Stroke(width = 8f)
                )
                drawIntoCanvas { canvas ->
                    val paint = android.graphics.Paint().apply {
                        color = android.graphics.Color.YELLOW
                        textSize = 40f
                        textAlign = android.graphics.Paint.Align.LEFT
                    }
                    canvas.nativeCanvas.drawText(
                        text,
                        boundingBox.left.toFloat(),
                        boundingBox.top.toFloat() - 10,
                        paint
                    )
                }
            }
        }
    }
}
