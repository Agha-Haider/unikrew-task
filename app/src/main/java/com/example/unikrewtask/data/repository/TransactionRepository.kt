package com.example.unikrewtask.data.repository

import androidx.lifecycle.LiveData
import com.example.unikrewtask.data.db.TransactionDao
import com.example.unikrewtask.data.model.Transaction
import kotlinx.coroutines.flow.Flow

class TransactionRepository(private  val transactionDao: TransactionDao) {


  suspend fun insertToDb(transaction: Transaction){
      transactionDao.insertTransaction(transaction)
  }

     fun getAllTransaction():Flow<List<Transaction>>{
        return transactionDao.getAllTransactions()
    }

    suspend fun getAllTransactionByType(type:String) :LiveData<List<Transaction>>{
        return transactionDao.getTransactionsByType(type)
    }
}