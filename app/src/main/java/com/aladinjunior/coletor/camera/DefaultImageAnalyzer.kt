package com.aladinjunior.coletor.camera

import android.content.Context
import androidx.camera.core.ImageAnalysis
import androidx.camera.mlkit.vision.MlKitAnalyzer
import androidx.core.content.ContextCompat

import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode

class DefaultImageAnalyzer(
    context: Context,
    val onBarcodesDetected: (List<Barcode>) -> Unit
) {


    private val options = BarcodeScannerOptions.Builder()
        .setBarcodeFormats(
            Barcode.FORMAT_CODE_128,
            Barcode.FORMAT_CODE_39,
            Barcode.FORMAT_CODE_93,
            Barcode.FORMAT_CODABAR,
            Barcode.FORMAT_EAN_13,
            Barcode.FORMAT_EAN_8,
            Barcode.FORMAT_ITF,
            Barcode.FORMAT_UPC_A,
            Barcode.FORMAT_UPC_E,
        )
        .build()

    private val scanner = BarcodeScanning.getClient(options)

    val mlKitAnalyzer = MlKitAnalyzer(
        listOf(scanner),
        ImageAnalysis.COORDINATE_SYSTEM_VIEW_REFERENCED,
        ContextCompat.getMainExecutor(context)
    ) { result ->
        val barcodes = result.getValue(scanner)
        if (barcodes != null)
            onBarcodesDetected(barcodes)



    }
}

