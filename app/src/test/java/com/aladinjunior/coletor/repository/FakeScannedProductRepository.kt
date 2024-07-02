package com.aladinjunior.coletor.repository

import com.aladinjunior.coletor.main.data.db.ScannedProduct
import com.aladinjunior.coletor.main.domain.repository.ScannedProductRepository

class FakeScannedProductRepository(

) : ScannedProductRepository {

    override suspend fun insertScannedProduct(product: ScannedProduct) {

    }
}