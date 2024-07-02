package com.aladinjunior.coletor

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.test.core.app.ApplicationProvider
import com.aladinjunior.coletor.camera.presentation.CameraViewModel
import com.aladinjunior.coletor.repository.FakeScannedProductRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class CameraViewModelTest {

    private lateinit var viewModel: CameraViewModel

    @Before
    fun setup() {
        viewModel = CameraViewModel(
            FakeScannedProductRepository()
        )
    }

    @Test
    fun create_stockCode_correctly() {

        val barcode = "555"
        val quantity = 70
        val expectedStockCode = "55570"

        val actualStockCode = viewModel.createStockCode(barcode, quantity)

        assertEquals(expectedStockCode, actualStockCode)

    }

}