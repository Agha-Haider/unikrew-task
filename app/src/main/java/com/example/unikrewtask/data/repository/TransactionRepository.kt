package com.example.unikrewtask.data.repository

import androidx.lifecycle.LiveData
import com.example.unikrewtask.data.db.TransactionDao
import com.example.unikrewtask.data.model.Transaction

class TransactionRepository(private  val transactionDao: TransactionDao) {


  suspend fun insertToDb(transaction: Transaction){
      transactionDao.insertTransaction(transaction)
  }

    suspend fun getAllTransaction():LiveData<List<Transaction>>{
        return transactionDao.getAllTransactions()
    }

    suspend fun getAllTransactionByType(type:String) :LiveData<List<Transaction>>{
        return transactionDao.getTransactionsByType(type)
    }
}