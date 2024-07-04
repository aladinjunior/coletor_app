package com.aladinjunior.coletor.camera.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CameraViewModel : ViewModel() {

    private val _isCollectRunning = MutableStateFlow(false)
    val isCollectRunning = _isCollectRunning.asStateFlow()

    private val _mostRecentBarcode = MutableStateFlow("")
    val mostRecentBarcode = _mostRecentBarcode.asStateFlow()

    private val _itemQuantity = MutableStateFlow("")
    val itemQuantity = _itemQuantity.asStateFlow()


    fun startCollect() {
        _isCollectRunning.value = true
    }

    fun finalizeCollect() {
        _isCollectRunning.value = false
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

    fun createStockCode(barcode: String, quantity: String): String {
        val builder = StringBuilder()
        builder.append(barcode).append(quantity)
        return builder.toString()
    }


    object CameraViewModelProvider {

        val factory = viewModelFactory {

            initializer {
                CameraViewModel()
            }
        }
    }
}