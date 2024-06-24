package com.aladinjunior.coletor.main.data.db

import androidx.room.Dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface ScannedProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(scannedProduct: ScannedProduct)

}