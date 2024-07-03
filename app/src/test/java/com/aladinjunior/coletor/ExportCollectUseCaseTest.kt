package com.aladinjunior.coletor

import com.aladinjunior.coletor.main.domain.repository.ScannedProductRepository
import com.aladinjunior.coletor.main.domain.usecase.ExportCollectUseCase
import com.aladinjunior.coletor.repository.FakeScannedProductRepository
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.io.path.createTempDirectory


class ExportCollectUseCaseTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var exportCollectUseCase: ExportCollectUseCase
    private lateinit var scannedProductRepository: ScannedProductRepository

    @Before
    fun setup() {
        scannedProductRepository = FakeScannedProductRepository()
        exportCollectUseCase = ExportCollectUseCase(testDispatcher, scannedProductRepository)
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

        val createdFile = exportCollectUseCase(tempDir, fileName, fileContent)

        val fileExists = createdFile?.exists()
        assertTrue("File exists", fileExists!!)

        println("File created at: ${createdFile.absolutePath}")
        createdFile.deleteRecursively()


    }
}