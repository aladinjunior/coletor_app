package com.aladinjunior.coletor.repository

import com.aladinjunior.coletor.main.domain.repository.FileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.withContext
import java.io.File
import java.io.IOException

class FakeFileRepository(
    private val dispatcher: TestDispatcher = StandardTestDispatcher()
) : FileRepository {

    override suspend fun createFile(pathName: String, fileName: String): File? {
        return withContext(dispatcher) {
            try {
                val file = File(pathName, fileName)
                file.createNewFile()
                file
            } catch (e: IOException) {
                null
            }
        }
    }

    override fun writeToFile(file: File, content: String) {
        TODO("Not yet implemented")
    }
}