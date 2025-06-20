package com.example.unikrewtask.ui.list

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.unikrewtask.R
import com.example.unikrewtask.databinding.FragmentTransactionListBinding
import com.example.unikrewtask.ui.adapter.TransactionAdapter
import com.example.unikrewtask.ui.add.AddTransViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class TransactionListFragment : Fragment() {
    private var _binding: FragmentTransactionListBinding? = null

    private val binding: FragmentTransactionListBinding? get() = _binding
    private lateinit var viewModel: AddTransViewModel

    private lateinit var adapter: TransactionAdapter


    private var startDate: Date ?=null
    private var endDate: Date? = null
    private val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTransactionListBinding.inflate(inflater, container, false)
        return _binding?.root    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= ViewModelProvider(this).get(AddTransViewModel::class.java)
        viewModel.filteredTransactions.observe(viewLifecycleOwner, Observer {
            adapter=TransactionAdapter(it)
            binding?.recyclerView?.adapter=adapter
            binding?.recyclerView?.layoutManager=LinearLayoutManager(activity)
            Log.d("haider", "haider testing data: "+it)
        })


        binding?.btnStartDate?.setOnClickListener { showStartDatePicker() }
        binding?.btnEndDate?.setOnClickListener { showEndDatePicker() }


        binding?.btnApplyFilter?.setOnClickListener {
            if (startDate != null && endDate != null) {
                viewModel.filterByDateRange(startDate!!, endDate!!)
            } else {
                Toast.makeText(requireContext(), "Select both dates", Toast.LENGTH_SHORT).show()
            }
        }

        binding?.btnReset?.setOnClickListener{
            viewModel.resetFilter()
        }
    }

    private fun showStartDatePicker() {
        val calendar = Calendar.getInstance()
        DatePickerDialog(requireContext(), { _, year, month, day ->
            calendar.set(year, month, day)
            startDate = calendar.time
            binding?.btnStartDate?.text =  dateFormat.format(startDate)
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
    }

    private fun showEndDatePicker() {
        val calendar = Calendar.getInstance()
        DatePickerDialog(requireContext(), { _, year, month, day ->
            calendar.set(year, month, day)
            endDate = calendar.time
            binding?.btnEndDate?.text = dateFormat.format(endDate)
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}