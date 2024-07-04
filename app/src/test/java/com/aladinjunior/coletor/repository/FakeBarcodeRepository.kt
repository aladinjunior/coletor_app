package com.aladinjunior.coletor.repository

import com.aladinjunior.coletor.main.data.db.ScannedProduct
import com.aladinjunior.coletor.main.domain.repository.BarcodeRepository

class FakeBarcodeRepository(

) : BarcodeRepository {

    private val productsList = mutableListOf<ScannedProduct>()
    private val stockCodesList = mutableListOf<String>()

    override suspend fun insertScannedProduct(product: ScannedProduct) {
        productsList.add(product)
    }

    override suspend fun fetchAllStockCode(): List<String> {
        productsList.forEach {
            stockCodesList.add(it.stockCode)
        }
        return stockCodesList
    }

}