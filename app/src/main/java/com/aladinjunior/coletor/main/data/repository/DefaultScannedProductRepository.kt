package com.aladinjunior.coletor.main.data.repository

import com.aladinjunior.coletor.main.data.db.ScannedProduct
import com.aladinjunior.coletor.main.data.db.ScannedProductDao
import com.aladinjunior.coletor.main.domain.repository.ScannedProductRepository

class DefaultScannedProductRepository(
    private val scannedProductDao: ScannedProductDao
) : ScannedProductRepository {

    override suspend fun insertScannedProduct(product: ScannedProduct) {
        scannedProductDao.insert(product)
    }

    override suspend fun fetchAllStockCode(): List<String> {
        return scannedProductDao.getAllStockCode()
    }


}