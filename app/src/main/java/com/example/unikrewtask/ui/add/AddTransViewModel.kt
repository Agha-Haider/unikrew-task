package com.example.unikrewtask.ui.add

import android.app.Application
import androidx.lifecycle.*
import com.example.unikrewtask.data.db.TransactionDatabase
import com.example.unikrewtask.data.model.Transaction
import com.example.unikrewtask.data.repository.TransactionRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class AddTransViewModel(application: Application) : AndroidViewModel(application) {

    private val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())

    private val transactionDao = TransactionDatabase.getInstance(application).subscriberDAO
    private val repository = TransactionRepository(transactionDao)

    private val _allTransactions = MutableLiveData<List<Transaction>>()
    val allTransactions: LiveData<List<Transaction>> = _allTransactions

    private val _filteredTransactions = MutableLiveData<List<Transaction>>()
    val filteredTransactions: LiveData<List<Transaction>> = _filteredTransactions

    val incomeFlow: StateFlow<Double>
    val expenseFlow: StateFlow<Double>
    val netSavings: StateFlow<Double>

    init {

        viewModelScope.launch {
            repository.getAllTransaction().collect { list ->
                _allTransactions.value = list
                _filteredTransactions.value = list
            }
        }




        val income = repository.getAllTransactionByType("Income")
        val expense = repository.getAllTransactionExpence("Expense")

        incomeFlow = income.stateIn(viewModelScope, SharingStarted.Eagerly, 0.0)
        expenseFlow = expense.stateIn(viewModelScope, SharingStarted.Eagerly, 0.0)

        netSavings = combine(incomeFlow, expenseFlow) { incomeAmt, expenseAmt ->
            incomeAmt - expenseAmt
        }.stateIn(viewModelScope, SharingStarted.Eagerly, 0.0)
    }

    fun insertTransaction(transaction: Transaction) = viewModelScope.launch {
        try {
            repository.insertToDb(transaction)
        } catch (e: Exception) {
        }
    }

    fun filterByDateRange(startDate: Date, endDate: Date) {
        val fullList = _allTransactions.value ?: return

        val filteredList = fullList.filter { transaction ->
            try {
                val txnDate = dateFormat.parse(transaction.date)
                txnDate != null && !txnDate.before(startDate) && !txnDate.after(endDate)
            } catch (e: Exception) {
                false
            }
        }

        _filteredTransactions.value = filteredList
    }

    fun resetFilter() {
        _filteredTransactions.value = _allTransactions.value
    }
}
