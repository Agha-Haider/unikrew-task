package com.example.unikrewtask.ui.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.unikrewtask.data.db.TransactionDatabase
import androidx.lifecycle.*
import com.example.unikrewtask.data.model.Transaction
import kotlinx.coroutines.launch
import com.example.unikrewtask.data.repository.TransactionRepository

class AddTransViewModel (application: Application) : AndroidViewModel(application)  {

    private val transactionDao=TransactionDatabase.getInstance(application).subscriberDAO
    private val repository=TransactionRepository(transactionDao)


    fun insertTransaction(transaction: Transaction) = viewModelScope.launch {
        try {
            repository.insertToDb(transaction)
        } catch (e: Exception) {

        }
    }

}