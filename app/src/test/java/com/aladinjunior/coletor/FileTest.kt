
package com.aladinjunior.coletor

import com.aladinjunior.coletor.main.data.repository.DefaultFileRepository
import com.aladinjunior.coletor.main.domain.repository.FileRepository
import com.aladinjunior.coletor.main.domain.repository.BarcodeRepository
import com.aladinjunior.coletor.main.domain.usecase.ExportCollectUseCase
import io.mockk.InternalPlatformDsl.toStr
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test
import java.io.File
import kotlin.io.path.createTempDirectory


class FileTest {


    private lateinit var exportCollectUseCase: ExportCollectUseCase
    private lateinit var mockBarcodeRepository: BarcodeRepository
    private lateinit var mockFileRepository: FileRepository

    private val fileName = "testFile.txt"
    private val tempDir = createTempDirectory("testFile").toString()
    private lateinit var testFile: File

    private val testScheduler = TestCoroutineScheduler()
    private val testDispatcher = StandardTestDispatcher(testScheduler)
    private val testScope = TestScope(testDispatcher)

    private lateinit var mockFile: File

    @Before
    fun setup() {
        mockBarcodeRepository = mockk()
        mockFileRepository = mockk(relaxed = true)
        exportCollectUseCase =
            ExportCollectUseCase(testDispatcher, mockBarcodeRepository, mockFileRepository)

        testFile = File(tempDir, fileName).apply {
            createNewFile()
        }

        mockFile = File(tempDir, fileName)

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

        coEvery { mockFileRepository.createFile(any(), any()) } returns testFile

        val createdFile = mockFileRepository.createFile(tempDir, fileName)

        val fileExists = createdFile?.exists()
        assertTrue("File exists", fileExists!!)

        println("File created at: ${createdFile.absolutePath}")


    }


    @Test
    fun whenExportCollect_stockCodesAreReturnedInFile() = testScope.runTest {
        val mockStockCodes = listOf(
            "AAA111", "BBB222", "CCC333"
        )


        // Mock the object and methods

        coEvery { mockBarcodeRepository.fetchAllStockCode()  } returns mockStockCodes
        coEvery { mockFileRepository.createFile(any(), any()) } returns mockFile
        every { mockFileRepository.writeToFile(mockFile, mockStockCodes) } answers {
            mockFile.writeText(mockStockCodes.toString())
        }


        // Run the use case and get the text of the content of the created file
        val actualFileContent = exportCollectUseCase(tempDir, fileName)?.readText()

        assertTrue(actualFileContent?.isNotEmpty() == true)
        assertTrue(actualFileContent == mockStockCodes.toString())

        // Verify that the repository method was called
        coVerify {  mockBarcodeRepository.fetchAllStockCode()   }


    }

    @Test
    fun whenWriteFile_stockCodesAreWrittenInCorrectFormat() = testScope.runTest {

        val mockStockCodesFromDb = listOf(
            "ABC1", "ABC2", "ABC3", "ABC4"
        )

        val expectedStockCodesFormat = "ABC1\nABC2\nABC3\nABC4"



        coEvery { mockFileRepository.createFile(any(), any()) } returns mockFile
        every { mockFileRepository.writeToFile(mockFile, mockStockCodesFromDb) } answers {
            val expectedFormatStockCodes = mockStockCodesFromDb.joinToString(separator = "\n")
            mockFile.writeText(expectedFormatStockCodes)
        }

        mockFileRepository.writeToFile(mockFile, mockStockCodesFromDb)

        verify { mockFileRepository.writeToFile(mockFile, mockStockCodesFromDb) }


        assertEquals(expectedStockCodesFormat, mockFile.readText())


    }


    @Test
    fun contentNotWrittenInFile_whenWriteFile_errorMessageIsReturned() {
        val fileRepository = DefaultFileRepository()

        val exception = assertThrows(IllegalArgumentException::class.java) {
            fileRepository.writeToFile(mockFile, emptyList())
        }

        assertEquals("content is null or empty!", exception.message)

    }



}