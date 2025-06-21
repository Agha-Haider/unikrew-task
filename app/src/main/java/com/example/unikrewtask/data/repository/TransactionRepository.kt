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

    fun getAllTransactionByType(type: String): Flow<Double> {
        return transactionDao.getTotalAmountByType(type)
    }

    fun getAllTransactionExpence(type: String): Flow<Double> {
        return transactionDao.getTotalAmountByType(type)
    }
//    fun getNetSavings(): LiveData<Double> {
//        return transactionDao.getNetSavings()
//    }
}