package com.aladinjunior.coletor.main.domain.usecase

import com.aladinjunior.coletor.main.domain.repository.ScannedProductRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class GenerateFileUseCase(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val scannedProductRepository: ScannedProductRepository
) {

    suspend operator fun invoke(pathName: String, fileName: String, content: String) : File? {
        return withContext(dispatcher) {
            try {
                val file = File(pathName, fileName)
                file.writeText(content)
                file
            } catch (e: Exception) {
                 null
            }
        }
    }
}