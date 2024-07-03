package com.aladinjunior.coletor

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.aladinjunior.coletor.main.data.db.AppDatabase
import com.aladinjunior.coletor.main.data.db.ScannedProduct
import com.aladinjunior.coletor.main.data.db.ScannedProductDao
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class ScannedProductDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var scannedProductDao: ScannedProductDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        scannedProductDao = database.scannedProductDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun getAll_stockCodes_fromDatabase() = runBlocking {
        val barcode1 = ScannedProduct.ScannedProductBuilder()
            .setBarcode("ABCD")
            .setQuantity("10")
            .setStockCode("ABCD10")
            .build()
        val barcode2 = ScannedProduct.ScannedProductBuilder()
            .setBarcode("EFGH")
            .setQuantity("20")
            .setStockCode("EFGH20")
            .build()
        scannedProductDao.insert(barcode1)
        scannedProductDao.insert(barcode2)

        val stockCodes = scannedProductDao.getAllStockCode()

        println("current stock codes: $stockCodes")

        assert(stockCodes.contains("ABCD10"))
        assert(stockCodes.contains("EFGH20"))

    }



}