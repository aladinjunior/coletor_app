package com.aladinjunior.coletor.main.domain.repository

import com.aladinjunior.coletor.main.data.db.ScannedProduct

interface ScannedProductRepository {

    suspend fun insertScannedProduct(product: ScannedProduct)

    suspend fun fetchAllStockCode() : List<String>





}