package com.aladinjunior.coletor.repository

import com.aladinjunior.coletor.main.domain.repository.ScannedProductRepository
import org.junit.Before
import org.junit.Test

class ScannedProductRepositoryTest {

    private lateinit var scannedProductRepository: ScannedProductRepository

    @Before
    fun setup() {
        scannedProductRepository = FakeScannedProductRepository()
    }

    @Test
    fun when


}