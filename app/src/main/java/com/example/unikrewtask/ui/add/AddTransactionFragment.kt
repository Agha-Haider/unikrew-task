package com.example.unikrewtask.ui.add

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import com.example.unikrewtask.R
import com.example.unikrewtask.data.model.Transaction
import com.example.unikrewtask.databinding.FragmentAddTransactionBinding
import com.google.android.material.datepicker.MaterialDatePicker


class AddTransactionFragment : Fragment() {
    private var _binding: FragmentAddTransactionBinding? = null
    private val binding: FragmentAddTransactionBinding? get() = _binding

    private lateinit var viewModel: AddTransViewModel

    private lateinit var appContext: Context
    var selectedType = "Income"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddTransactionBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appContext = context
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[AddTransViewModel::class.java]
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {

                }
            })
        
        val categories = listOf("Food", "Transport", "Salary", "Rent")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, categories)
        binding?.categoryInput?.setAdapter(adapter)


        binding?.categoryInput?.setOnClickListener {
            binding?.categoryInput?.showDropDown()
        }

        val typeList = listOf("Income", "Expense")
        val typeAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, typeList)
        binding?.typeInput?.setAdapter(typeAdapter)

        binding?.typeInput?.setOnClickListener {
            binding?.typeInput?.showDropDown()
        }

        binding?.saveTransactionBtn?.setOnClickListener {
            if (checkValidation()) {
                val trasaction = Transaction(
                    8,
                    binding?.typeInput?.text.toString(),
                    binding?.amountInput?.text.toString(),
                    binding?.typeInput?.text.toString(),
                    binding?.dateInput?.text.toString(),
                    binding?.descriptionInput?.text.toString()

                )
                viewModel.insertTransaction(trasaction)

                binding?.amountInput?.text?.clear()
                binding?.categoryInput?.text?.clear()
//                binding?.ty?.text?.clear()
                binding?.dateInput?.text?.clear()
                binding?.descriptionInput?.text?.clear()
            }
            else{
                Toast.makeText(appContext, "please fill all mandatory fields", Toast.LENGTH_SHORT).show()
            }

        }

        binding?.dateInput?.setOnClickListener {
            val datePicker: MaterialDatePicker<Long> =
                MaterialDatePicker.Builder
                    .datePicker()
                    .setTitleText("Choose a date")
                    .build()

            datePicker.show(parentFragmentManager, "DATE_PICKER")

            datePicker.addOnPositiveButtonClickListener { selection ->
                val sdf =
                    java.text.SimpleDateFormat("dd MMM yyyy", java.util.Locale.getDefault())
                val formattedDate = sdf.format(java.util.Date(selection))
                binding?.dateInput?.setText(formattedDate)
            }
        }
    }



        private fun checkValidation(): Boolean {
            val amount = binding?.amountInput?.text.toString()
            val category = binding?.categoryInput?.text.toString()
            val date = binding?.dateInput?.text.toString()
            if (TextUtils.isEmpty(amount) || (amount.equals("Amount")) || TextUtils.isEmpty(category) || (category.equals("Category"))|| TextUtils.isEmpty(date) || date.equals("Date")) {
                return false
            }
            return true
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
