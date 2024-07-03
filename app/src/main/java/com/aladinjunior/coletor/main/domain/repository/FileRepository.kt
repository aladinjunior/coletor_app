package com.aladinjunior.coletor.main.domain.repository

import java.io.File

interface FileRepository {

    suspend fun createFile(pathName: String, fileName: String) : File?

    fun writeToFile(file: File, content: String)

}