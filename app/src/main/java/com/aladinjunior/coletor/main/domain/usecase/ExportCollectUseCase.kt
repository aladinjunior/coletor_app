package com.aladinjunior.coletor.main.domain.usecase

import com.aladinjunior.coletor.main.domain.repository.FileRepository
import com.aladinjunior.coletor.main.domain.repository.ScannedProductRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class ExportCollectUseCase(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val scannedProductRepository: ScannedProductRepository,
    private val fileRepository: FileRepository,
) {

    suspend operator fun invoke(pathName: String, fileName: String, content: String) {
        return withContext(dispatcher) {
            try {
                val createdFile = fileRepository.createFile(pathName, fileName)
                val content = scannedProductRepository.fetchAllStockCode().toString()
                if (createdFile != null) fileRepository.writeToFile(createdFile, content)
            } catch (e: Exception) {
                 null
            }
        }
    }


}


