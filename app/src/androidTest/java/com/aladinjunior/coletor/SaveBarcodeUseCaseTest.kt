package com.aladinjunior.coletor

import com.aladinjunior.coletor.db.getTestDatabase
import com.aladinjunior.coletor.main.data.db.AppDatabase
import com.aladinjunior.coletor.main.data.db.ScannedProduct
import com.aladinjunior.coletor.main.data.db.ScannedProductDao
import com.aladinjunior.coletor.main.data.repository.DefaultBarcodeRepository
import com.aladinjunior.coletor.main.domain.usecase.SaveBarcodeUseCase
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class SaveBarcodeUseCaseTest {

    private lateinit var saveBarcodeUseCase: SaveBarcodeUseCase
    private lateinit var scannedProductDao: ScannedProductDao
    private lateinit var database: AppDatabase



    @Before
    fun setup() {
        database = getTestDatabase
        scannedProductDao = database.scannedProductDao()
        saveBarcodeUseCase = SaveBarcodeUseCase(
            DefaultBarcodeRepository(scannedProductDao))
    }

    @After
    fun tearDown(){
        database.close()
    }

    @Test
    fun whenSaveBarcodeUseCase_shouldInsertOnDatabase()  = runTest {
        val barcode = "ABC"
        val quantity = 10

        saveBarcodeUseCase(barcode, quantity)

        val expectedProductInserted = ScannedProduct.ScannedProductBuilder()
            .setBarcode("ABC")
            .setQuantity(10)
            .setStockCode("ABC10")
            .build()

        val allStockCodes = scannedProductDao.getAllStockCode()

        assertTrue(allStockCodes.contains(expectedProductInserted.stockCode))


    }


}