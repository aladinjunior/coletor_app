package com.aladinjunior.coletor.camera.presentation

import android.content.Context
import androidx.camera.view.LifecycleCameraController
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CameraViewModel : ViewModel() {

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
}