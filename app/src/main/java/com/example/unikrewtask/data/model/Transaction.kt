package com.example.unikrewtask.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction_table")
data  class Transaction (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "category")
    val category: String,

    @ColumnInfo(name = "amount")
    val amount: String,

    @ColumnInfo(name = "type")
    val type: String,

    @ColumnInfo(name = "date")
    val date: String,

    @ColumnInfo(name = "description")
    val description: String? = null
)