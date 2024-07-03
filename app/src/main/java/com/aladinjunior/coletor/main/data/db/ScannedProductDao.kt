package com.aladinjunior.coletor.main.data.db

import androidx.room.Dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ScannedProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(scannedProduct: ScannedProduct)

    @Query("SELECT stockCode FROM scanned_products")
    suspend fun getAllStockCode() : List<String>

}