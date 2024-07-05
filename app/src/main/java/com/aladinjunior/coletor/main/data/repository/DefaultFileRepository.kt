package com.aladinjunior.coletor.main.data.repository

import android.util.Log
import com.aladinjunior.coletor.main.domain.repository.FileRepository
import java.io.File
import java.io.IOException

class DefaultFileRepository : FileRepository {

    override suspend fun createFile(pathName: String, fileName: String): File? {
        return try {
            val file = File(pathName, fileName)
            file.createNewFile()
            file
        } catch (e: IOException) {
            null
        }

    }

    override fun writeToFile(file: File, content: List<String>) {

        if (content.isEmpty()) {
            throw IllegalArgumentException("content is null or empty!")
        }
        val breakedUpLinesContent = content.joinToString(separator = "\n")
        file.writeText(breakedUpLinesContent)


    }


}

