package com.aladinjunior.coletor

import com.aladinjunior.coletor.camera.presentation.CameraViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CameraViewModelTest {

    private lateinit var viewModel: CameraViewModel

    @Before
    fun setup() {
        viewModel = CameraViewModel()
    }

    @Test
    fun whenCreateStockCode_barcodeAndQuantityAreJoined() {

        val barcode = "555"
        val quantity = "70"
        val expectedStockCode = "55570"

        val actualStockCode = viewModel.createStockCode(barcode, quantity)

        assertEquals(expectedStockCode, actualStockCode)

    }

}