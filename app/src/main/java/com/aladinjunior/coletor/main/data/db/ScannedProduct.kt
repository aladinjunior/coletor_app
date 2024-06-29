package com.aladinjunior.coletor.main.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "scanned_products")
data class ScannedProduct(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val barcode: String,
    val quantity: Int,
    val stockCode: String,
)