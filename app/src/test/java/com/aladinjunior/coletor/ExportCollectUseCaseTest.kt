
package com.aladinjunior.coletor

import com.aladinjunior.coletor.main.domain.repository.FileRepository
import com.aladinjunior.coletor.main.domain.repository.BarcodeRepository
import com.aladinjunior.coletor.main.domain.usecase.ExportCollectUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.File
import kotlin.io.path.createTempDirectory


class ExportCollectUseCaseTest {


    private lateinit var exportCollectUseCase: ExportCollectUseCase
    private lateinit var mockBarcodeRepository: BarcodeRepository
    private lateinit var mockFileRepository: FileRepository

    private val fileName = "testFile.txt"
    private val tempDir = createTempDirectory("testFile").toString()
    private lateinit var testFile: File

    private val testScheduler = TestCoroutineScheduler()
    private val testDispatcher = StandardTestDispatcher(testScheduler)
    private val testScope = TestScope(testDispatcher)


    @Before
    fun setup() {
        mockBarcodeRepository = mockk()
        mockFileRepository = mockk()
        exportCollectUseCase =
            ExportCollectUseCase(testDispatcher, mockBarcodeRepository, mockFileRepository)

        testFile = File(tempDir, fileName).apply {
            createNewFile()
        }
    }

    @After
    fun tearDown() {
        testDispatcher.cancel()
        testFile.deleteRecursively()
    }


    @Test
    fun whenCreateFile_fileIsReturned() = runTest {


        val fileName = "testFile.txt"
        val tempDir = createTempDirectory("testDir").toString()


        val createdFile = mockFileRepository.createFile(tempDir, fileName)

        val fileExists = createdFile?.exists()
        assertTrue("File exists", fileExists!!)

        println("File created at: ${createdFile.absolutePath}")


    }

    @Test
    fun whenWriteContent_contentIsWrittenInFile() = runTest {
        val content = "hello world! write content test!"

        mockFileRepository.writeToFile(testFile, content)

        val textInFile = testFile.readText()

        assertTrue(textInFile == content)

        println("content: $content")
        println("textInFile: $textInFile")

    }


    @Test
    fun whenExportCollect_stockCodesAreReturnedInFile() = testScope.runTest {
        val mockStockCodes = listOf(
            "AAA111", "BBB222", "CCC333"
        )

        // Mock the object and methods
        val mockFile = File(tempDir, fileName)
        coEvery { mockBarcodeRepository.fetchAllStockCode()  } returns mockStockCodes
        coEvery { mockFileRepository.createFile(any(), any()) } returns mockFile
        every { mockFileRepository.writeToFile(mockFile, mockStockCodes.toString()) } answers {
            mockFile.writeText(mockStockCodes.toString())
        }

        // Run the use case and get the text of the content of the created file
        val actualFileContent = exportCollectUseCase(tempDir, fileName)?.readText()

        assertTrue(actualFileContent?.isNotEmpty() == true)
        assertTrue(actualFileContent == mockStockCodes.toString())

        // Verify that the repository method was called
        coVerify {  mockBarcodeRepository.fetchAllStockCode()   }




    }



}