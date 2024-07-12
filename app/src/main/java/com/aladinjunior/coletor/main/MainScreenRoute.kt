package com.aladinjunior.coletor.main

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import com.aladinjunior.coletor.camera.presentation.CameraViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aladinjunior.coletor.main.presentation.MainViewModel
import com.aladinjunior.coletor.main.presentation.MainViewModelProvider
import kotlinx.coroutines.flow.collect

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
    val context = LocalContext.current
    val isCollectionRunning by cameraViewModel.isCollectRunning.collectAsState()
    val quantityText by cameraViewModel.itemQuantity.collectAsState()
    val barcode by cameraViewModel.mostRecentBarcode.collectAsState()
    val exportedFile by mainViewModel.fileFlow.collectAsState()
    var shareIntent: Intent? = null



    LaunchedEffect(exportedFile) {
        if (exportedFile != null) {
            shareIntent = mainViewModel.createShareIntent2(context, exportedFile!!)
            startActivity(context, shareIntent!!, null)
            Log.d("testing123", "MainScreenRoute2: share intent created successfully!")
        } else {
            Log.d("testing123", "MainScreenRoute2: share intent NOT created successfully!")
        }
    }

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
        exportCollect = {
            val directory = context.getExternalFilesDir(null)
            mainViewModel.exportCollect(directory!!.absolutePath, "test123.txt")


            exportedFile?.let {
                shareIntent = mainViewModel.createShareIntent(it)
            }
            if (shareIntent != null) {
                startActivity(context, shareIntent!!, null)
                Log.d("testing123", "MainScreenRoute: share intent created successfully!")

            } else {
                Log.d("testing123", "MainScreenRoute: share intent NOT created successfully!")

            }



        },
    )
}
