package com.aladinjunior.coletor.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.aladinjunior.coletor.camera.presentation.CameraViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aladinjunior.coletor.main.data.db.ScannedProduct
import com.aladinjunior.coletor.main.presentation.MainViewModel
import com.aladinjunior.coletor.main.presentation.MainViewModelProvider

@Composable
fun MainScreenRoute(
    cameraViewModel: CameraViewModel = viewModel(
        factory = CameraViewModel.CameraViewModelProvider.factory
    ),
    mainViewModel: MainViewModel = viewModel(
        factory =
        MainViewModelProvider.provideFactory(LocalContext.current)
    )

) {
    val isCollectionRunning by cameraViewModel.isCollectRunning.collectAsState()
    val quantityText by cameraViewModel.itemQuantity.collectAsState()
    val barcode by cameraViewModel.mostRecentBarcode.collectAsState()

    MainScreen(
        mostRecentBarcode = {
            it?.let { notNullMostRecentBarcode ->
                cameraViewModel.setCurrentBarcode(notNullMostRecentBarcode)
            }
        },
        startCollect = cameraViewModel::startCollect,

        finalizeCollect = cameraViewModel::finalizeCollect,

        isCollectionRunning = isCollectionRunning,

        onSaveBarcode = {

            mainViewModel.saveBarcode(barcode, quantityText.toInt())
            cameraViewModel.resetBarcodeValues()

        },
        quantityFieldText = quantityText,

        onQuantityFieldValueChange = {
            cameraViewModel.setCurrentItemQuantity(it)
        },


        )
}