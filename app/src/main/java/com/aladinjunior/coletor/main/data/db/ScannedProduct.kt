package com.aladinjunior.coletor.main.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "scanned_products")
data class ScannedProduct(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val barcode: String,
    val quantity: String,
    val stockCode: String,
) {
    class ScannedProductBuilder {
        private var id: Long = 0
        private var barcode: String = ""
        private var quantity: String = ""
        private var stockCode: String = ""

        fun setId(id: Long) = apply { this.id = id }
        fun setBarcode(barcode: String) = apply { this.barcode = barcode }
        fun setQuantity(quantity: String) = apply { this.quantity = quantity }
        fun setStockCode(stockCode: String) = apply { this.stockCode = stockCode }

        fun build(): ScannedProduct {
            return ScannedProduct(
                id = id,
                barcode = barcode,
                quantity = quantity,
                stockCode = stockCode
            )
        }
    }
}