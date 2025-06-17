package com.example.unikrewtask.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.unikrewtask.data.model.Transaction


@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTransaction(transaction: Transaction)

    @Query("SELECT * FROM transaction_table ORDER BY date DESC")
    fun getAllTransactions(): LiveData<List<Transaction>>


    @Query("SELECT * FROM transaction_table WHERE type = :type ORDER BY date DESC")
    fun getTransactionsByType(type: String): LiveData<List<Transaction>>

}