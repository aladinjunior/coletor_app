package com.aladinjunior.coletor

import com.aladinjunior.coletor.main.domain.repository.ScannedProductRepository
import com.aladinjunior.coletor.main.domain.usecase.GenerateFileUseCase
import com.aladinjunior.coletor.repository.FakeScannedProductRepository
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.io.path.createTempDirectory


class GenerateFileUseCaseTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var generateFileUseCase: GenerateFileUseCase
    private lateinit var scannedProductRepository: ScannedProductRepository

    @Before
    fun setup() {
        scannedProductRepository = FakeScannedProductRepository()
        generateFileUseCase = GenerateFileUseCase(testDispatcher, scannedProductRepository)
    }

    @After
    fun tearDown() {
        testDispatcher.cancel()
    }


    @Test
    fun whenGenerateFile_textFileIsReturned() = runTest(testDispatcher) {
        val fileName = "testFile.txt"
        val tempDir = createTempDirectory("testDir").toString()
        val fileContent = "hello world!"

        val createdFile = generateFileUseCase(tempDir, fileName, fileContent)

        val fileExists = createdFile?.exists()
        assertTrue("File exists", fileExists!!)

        println("File created at: ${createdFile.absolutePath}")
        createdFile.deleteRecursively()


    }
}