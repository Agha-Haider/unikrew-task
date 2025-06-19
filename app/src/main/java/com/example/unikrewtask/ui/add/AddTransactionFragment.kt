package com.example.unikrewtask.ui.add

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.unikrewtask.R
import com.example.unikrewtask.data.model.Transaction
import com.example.unikrewtask.databinding.FragmentAddTransactionBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*

class AddTransactionFragment : Fragment() {

    private var _binding: FragmentAddTransactionBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: AddTransViewModel
    private lateinit var appContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[AddTransViewModel::class.java]


        val categories = listOf("Food", "Transport", "Salary", "Rent")
        val categoryAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, categories)
        binding.categoryInput.setAdapter(categoryAdapter)
        binding.categoryInput.setOnClickListener {
            binding.categoryInput.showDropDown()
        }
        binding.categoryInput.setOnItemClickListener { _, _, position, _ ->
            binding.categoryInput.setText(categories[position], false)
        }

        val typeList = listOf("Income", "Expense")
        val typeAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, typeList)
        binding?.typeInput?.setAdapter(null) // clear old one
        binding?.typeInput?.setAdapter(typeAdapter)


        binding?.typeInput?.setOnItemClickListener { _, _, position, _ ->
            binding?.typeInput?.setText(typeList[position], false)
        }

        binding?.typeInput?.setOnClickListener{
            binding?.typeInput?.showDropDown()
        }


        binding.dateInput.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Choose a date")
                .build()

            datePicker.show(parentFragmentManager, "DATE_PICKER")
            datePicker.addOnPositiveButtonClickListener { selection ->
                val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                val formattedDate = sdf.format(Date(selection))
                binding.dateInput.setText(formattedDate)
            }
        }

        binding.saveTransactionBtn.setOnClickListener {
            if (checkValidation()) {
                val transaction = Transaction(
                    id = 0,
                    category = binding.categoryInput.text.toString(),
                    amount = binding.amountInput.text.toString(),
                    type = binding.typeInput.text.toString(),
                    date = binding.dateInput.text.toString(),
                    description = binding.descriptionInput.text.toString()
                )

                viewModel.insertTransaction(transaction)

                Toast.makeText(requireContext(), "Transaction Saved", Toast.LENGTH_SHORT).show()

                clearFields()

                if (findNavController().currentDestination?.id == R.id.addTransactionFragment) {
                    findNavController().navigate(R.id.action_addTransactionFragment_to_transactionListFragment)
                }
            } else {
                Toast.makeText(requireContext(), "Please fill all required fields", Toast.LENGTH_SHORT).show()
            }
        }


        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

            }
        })
    }

    private fun clearFields() {
        binding.amountInput.text?.clear()
        binding.categoryInput.text?.clear()
        binding.typeInput.text?.clear()
        binding.dateInput.text?.clear()
        binding.descriptionInput.text?.clear()
    }

    private fun checkValidation(): Boolean {
        val amount = binding.amountInput.text.toString()
        val category = binding.categoryInput.text.toString()
        val type = binding.typeInput.text.toString()
        val date = binding.dateInput.text.toString()

        return !(TextUtils.isEmpty(amount)
                || TextUtils.isEmpty(category)
                || TextUtils.isEmpty(type)
                || TextUtils.isEmpty(date))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
