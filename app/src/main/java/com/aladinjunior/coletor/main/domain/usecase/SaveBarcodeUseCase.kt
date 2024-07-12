package com.aladinjunior.coletor.main.domain.usecase

import android.util.Log
import com.aladinjunior.coletor.main.data.db.ScannedProduct
import com.aladinjunior.coletor.main.domain.repository.BarcodeRepository
import java.text.SimpleDateFormat
import java.util.Locale

class SaveBarcodeUseCase(
    private val barcodeRepository: BarcodeRepository
) {


    suspend operator fun invoke(barcode: String, quantity: Int) {

        val quantityLength = quantity.toString().length
        val date = SimpleDateFormat(
            DATE_PATTERN,
            Locale.getDefault()
        ).format(System.currentTimeMillis())

        try {
            var stockCode = ""
            when (quantityLength) {
                1 -> stockCode = "0${barcode}00000${quantity}0000000$date"
                2 -> stockCode = "0${barcode}0000${quantity}0000000$date"
                3 -> stockCode = "0${barcode}000${quantity}0000000$date"
                4 -> stockCode = "0${barcode}00${quantity}0000000$date"
            }

            val product = ScannedProduct(
                barcode = barcode,
                quantity = quantity,
                stockCode = stockCode
            )

            barcodeRepository.insertScannedProduct(product)

        } catch (e: Exception) {
            val tag = "SaveBarcodeUseCase_Log"
            Log.d(tag, e.stackTraceToString())
        }

    }

    companion object {
        const val DATE_PATTERN = "dd/MM/yyHH:mm:ss"

    }

}