package com.aladinjunior.coletor.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    val quantityText by viewModel.itemQuantity.collectAsState()
    val barcode by viewModel.mostRecentBarcode.collectAsState()

    MainScreen(
        mostRecentBarcode = {
            it?.let { notNullMostRecentBarcode ->
                viewModel.setCurrentBarcode(notNullMostRecentBarcode)
            }
        },
        startCollect = viewModel::startCollect,

        finalizeCollect = viewModel::finalizeCollect,

        isCollectionRunning = isCollectionRunning,

        onSaveBarcode = {
            val stockCode = viewModel.createStockCode(barcode, quantityText)
            val scannedProduct = ScannedProduct.ScannedProductBuilder()
                .setBarcode(barcode)
                .setQuantity(quantityText)
                .setStockCode(stockCode)
                .build()
            with(viewModel) {
                saveBarcode(scannedProduct)
                resetBarcodeValues()
            }

        },
        quantityFieldText = quantityText,

        onQuantityFieldValueChange = {
            viewModel.setCurrentItemQuantity(it)
        },



    )
}