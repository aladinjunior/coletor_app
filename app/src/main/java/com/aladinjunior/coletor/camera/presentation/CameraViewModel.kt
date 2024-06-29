package com.aladinjunior.coletor.camera.presentation

import android.content.Context
import androidx.camera.view.LifecycleCameraController
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.aladinjunior.coletor.main.data.db.AppDatabase
import com.aladinjunior.coletor.main.data.db.ScannedProduct
import com.aladinjunior.coletor.main.data.repository.DefaultScannedProductRepository
import com.aladinjunior.coletor.main.domain.repository.ScannedProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CameraViewModel(
    private val scannedProductRepository: ScannedProductRepository
) : ViewModel() {

    private val _isCollectRunning = MutableStateFlow(false)
    val isCollectRunning = _isCollectRunning.asStateFlow()

    fun startCollect() {
        _isCollectRunning.value = true
    }
    fun saveBarcode(product: ScannedProduct) = viewModelScope.launch {
        scannedProductRepository.insertScannedProduct(product)
    }

    fun createStockCode(barcode: String, quantity: Int) : String {
        return ""
    }

    object CameraViewModelProvider {
        fun provideFactory(context: Context) = viewModelFactory {
            initializer {
                CameraViewModel(DefaultScannedProductRepository(AppDatabase.getDatabase(context).scannedProductDao()))
            }
        }
    }
}