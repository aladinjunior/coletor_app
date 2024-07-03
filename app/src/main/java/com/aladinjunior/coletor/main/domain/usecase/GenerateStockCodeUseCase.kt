package com.aladinjunior.coletor.main.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GenerateStockCodeUseCase() {

    fun createStockCode(barcode: String, quantity: Int) : Flow<String> = flow {
        val builder = StringBuilder()
        builder.append(barcode).append(quantity)
        emit(builder.toString())
    }

}