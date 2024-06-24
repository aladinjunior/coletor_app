package com.aladinjunior.coletor.camera.presentation

import android.content.Context
import androidx.camera.view.LifecycleCameraController
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.aladinjunior.coletor.main.data.db.AppDatabase
import com.aladinjunior.coletor.main.data.db.ScannedProduct
import com.aladinjunior.coletor.main.data.db.ScannedProductDao
import com.aladinjunior.coletor.main.data.repository.DefaultScannedProductRepository
import com.aladinjunior.coletor.main.domain.repository.ScannedProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CameraViewModel(
    private val scannedProductRepository: ScannedProductRepository
) : ViewModel() {

    fun saveScannedProduct(product: ScannedProduct) = viewModelScope.launch {
        scannedProductRepository.insertScannedProduct(product)
    }

    private var cameraController: LifecycleCameraController? = null

    private val _isCameraVisible = MutableStateFlow(true)



    fun getCameraController(context: Context): LifecycleCameraController {
        if (cameraController == null) {
            cameraController = LifecycleCameraController(context).apply {
                bindToLifecycle(context as androidx.lifecycle.LifecycleOwner)
            }
        }
        return cameraController!!
    }

    fun setCameraVisibility(isVisible: Boolean) {
        _isCameraVisible.value = isVisible
    }


    private val _number = MutableStateFlow(0)
    val number = _number.asStateFlow()

    fun increase() {
        _number.value++
    }

    override fun onCleared() {
        super.onCleared()
        cameraController?.unbind()
    }

    object CameraViewModelProvider {
        fun provideFactory(context: Context) = viewModelFactory {
            initializer {
                CameraViewModel(DefaultScannedProductRepository(AppDatabase.getDatabase(context).scannedProductDao()))
            }
        }
    }
}