package com.aladinjunior.coletor.main.domain.usecase

import android.util.Log
import com.aladinjunior.coletor.main.data.db.ScannedProduct
import com.aladinjunior.coletor.main.domain.repository.BarcodeRepository

class SaveBarcodeUseCase(
    private val barcodeRepository: BarcodeRepository
) {


    suspend operator fun invoke(barcode: String, quantity: Int) {

        try {
            val builder = StringBuilder()
            builder.append(barcode).append(quantity)

            val product = ScannedProduct(
                barcode = barcode,
                quantity = quantity,
                stockCode = builder.toString()
            )

            barcodeRepository.insertScannedProduct(product)

        } catch (e: Exception) {
            val tag = "SaveBarcodeUseCase_Log"
            Log.d(tag, e.stackTraceToString())
        }

    }

}