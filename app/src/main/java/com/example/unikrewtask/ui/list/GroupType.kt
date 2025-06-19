package com.example.unikrewtask.ui.list

import com.example.unikrewtask.data.model.Transaction

sealed class TransactionItem {
    data class Header(val title: String) : TransactionItem()
    data class Row(val transaction: Transaction) : TransactionItem()
}
