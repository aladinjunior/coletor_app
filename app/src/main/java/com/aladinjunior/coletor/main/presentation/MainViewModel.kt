package com.aladinjunior.coletor.main.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.aladinjunior.coletor.main.data.db.AppDatabase
import com.aladinjunior.coletor.main.data.repository.DefaultFileRepository
import com.aladinjunior.coletor.main.data.repository.DefaultBarcodeRepository
import com.aladinjunior.coletor.main.domain.usecase.ExportCollectUseCase
import com.aladinjunior.coletor.main.domain.usecase.SaveBarcodeUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    private val saveBarcodeUseCase: SaveBarcodeUseCase,
    private val exportCollectUseCase: ExportCollectUseCase
) : ViewModel() {

    fun saveBarcode(barcode: String, quantity: Int) = viewModelScope.launch {
        saveBarcodeUseCase(barcode, quantity)
    }

    fun exportCollect(pathName: String, fileName: String) = viewModelScope.launch {
        exportCollectUseCase("", "")
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
                )
            )
        }
    }
}