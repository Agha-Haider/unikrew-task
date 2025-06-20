package com.example.unikrewtask.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.unikrewtask.data.model.Transaction
import kotlinx.coroutines.flow.Flow


@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTransaction(transaction: Transaction)

    @Query("SELECT * FROM transaction_table ORDER BY date DESC")
    fun getAllTransactions(): Flow<List<Transaction>>


    @Query("SELECT COALESCE(SUM(CAST(amount AS REAL)), 0.0) FROM transaction_table WHERE type = :type")
    fun getTotalAmountByType(type: String): LiveData<Double>



}