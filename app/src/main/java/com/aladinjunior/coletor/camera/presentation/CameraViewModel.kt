package com.aladinjunior.coletor.camera.presentation

import android.content.Context
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

    private val _mostRecentBarcode = MutableStateFlow("")
    val mostRecentBarcode = _mostRecentBarcode.asStateFlow()

    private val _itemQuantity = MutableStateFlow("")
    val itemQuantity = _itemQuantity.asStateFlow()

    private val _isOpenedBottomSheet = MutableStateFlow(false)
    val isOpenedBottomSheet = _isOpenedBottomSheet.asStateFlow()

    fun startCollect() {
        _isCollectRunning.value = true
    }

    fun finalizeCollect() {
        _isCollectRunning.value = false
    }

    /**
     * when the BottomSheet is opened (true)
     * this function return (false)
     * which means that the app will not collect data untill the BottomSheet gets closed (false)
     */
    fun canCollect(): Boolean {
        _isOpenedBottomSheet.value = true
        return !isOpenedBottomSheet.value
    }

    fun setCurrentBarcode(barcode: String) {
        _mostRecentBarcode.value = barcode
    }

    fun setCurrentItemQuantity(quantity: String) {
        _itemQuantity.value = quantity
    }

    fun resetBarcodeValues() {
        _itemQuantity.value = ""
        _mostRecentBarcode.value = ""
    }


    fun saveBarcode(product: ScannedProduct) = viewModelScope.launch {
        scannedProductRepository.insertScannedProduct(product)
    }

    fun createStockCode(barcode: String, quantity: String): String {
        val builder = StringBuilder()
        builder.append(barcode).append(quantity)
        return builder.toString()
    }

    object CameraViewModelProvider {
        fun provideFactory(context: Context) = viewModelFactory {
            initializer {
                CameraViewModel(
                    DefaultScannedProductRepository(
                        AppDatabase.getDatabase(context).scannedProductDao()
                    )
                )
            }
        }
    }
}