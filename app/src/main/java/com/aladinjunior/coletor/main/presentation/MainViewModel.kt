package com.aladinjunior.coletor.main.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.aladinjunior.coletor.main.data.db.AppDatabase
import com.aladinjunior.coletor.main.data.repository.DefaultFileRepository
import com.aladinjunior.coletor.main.data.repository.DefaultBarcodeRepository
import com.aladinjunior.coletor.main.domain.usecase.ExportCollectUseCase
import com.aladinjunior.coletor.main.domain.usecase.SaveBarcodeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File

class MainViewModel(
    private val saveBarcodeUseCase: SaveBarcodeUseCase,
    private val exportCollectUseCase: ExportCollectUseCase,

) : ViewModel() {

    private val _fileFlow = MutableStateFlow<File?>(null)
    val fileFlow = _fileFlow.asStateFlow()
    fun saveBarcode(barcode: String, quantity: Int) = viewModelScope.launch {
        saveBarcodeUseCase(barcode, quantity)
    }

    fun exportCollect(pathName: String, fileName: String) = viewModelScope.launch {
        val exportedFile = exportCollectUseCase(pathName, fileName)
        _fileFlow.value = exportedFile
        if (_fileFlow.value != null) {
            Log.d("testing123", "file exists?: ${fileFlow.value?.exists()}")
            Log.d("testing123", "file content: ${fileFlow.value?.readText()}")
        } else {
            Log.d("testing123", "file not created")

        }

    }

    fun createShareIntent(file: File) : Intent {
        val uri = Uri.fromFile(file)
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_STREAM, uri)
            type = "text/plain"
        }
        return Intent.createChooser(sendIntent, null)
    }

    fun createShareIntent2(context: Context, file: File) : Intent {
        val uri = FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_STREAM, uri)
            type = "text/plain"
            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        }
        return Intent.createChooser(sendIntent, null)
    }


}

object MainViewModelProvider {
    fun provideFactory(context: Context) = viewModelFactory {
        val scannedProductRepository = DefaultBarcodeRepository(
            AppDatabase.getDatabase(context).scannedProductDao()
        )
        val fileRepository = DefaultFileRepository()
        initializer {
            MainViewModel(
                SaveBarcodeUseCase(scannedProductRepository),
                ExportCollectUseCase(
                    barcodeRepository = scannedProductRepository,
                    fileRepository = fileRepository
                ),
            )
        }
    }
}