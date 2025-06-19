package com.example.unikrewtask.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.unikrewtask.data.model.Transaction
import com.example.unikrewtask.databinding.ListItemBinding


class TransactionAdapter(
    private val originalList: List<Transaction>
) : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    var displayList: List<Transaction> = originalList

    inner class TransactionViewHolder(val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return TransactionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = displayList[position]

        holder.binding.tvCategory.text = "Category: ${transaction.category}"
        holder.binding.tvType.text = "Type: ${transaction.type}"
        holder.binding.tvAmount.text = "Amount: Rs ${transaction.amount}"
        holder.binding.tvDate.text = "Date: ${transaction.date}"
        holder.binding.tvDescription.text =
            if (transaction.description.isNullOrBlank()) "Description: N/A"
            else "Description: ${transaction.description}"
    }

    override fun getItemCount(): Int = displayList.size

    fun updateList(newList: List<Transaction>) {
        displayList = newList
        notifyDataSetChanged()
    }

    fun resetList() {
        displayList = originalList
        notifyDataSetChanged()
    }
}

