package com.example.unikrewtask.summary

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.unikrewtask.R
import com.example.unikrewtask.databinding.FragmentSummaryBinding
import com.example.unikrewtask.ui.add.AddTransViewModel
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SummaryFragment : Fragment() {

    private var _binding: FragmentSummaryBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: AddTransViewModel

    private var income = 0f
    private var expense = 0f
    private var savings = 0f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSummaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(AddTransViewModel::class.java)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.incomeFlow.collectLatest { amount ->
                income = amount.toFloat() ?: 0f
                binding.incomeAmount.setText(amount.toString())
                updateChart(income, expense, savings)
                Log.d("jjj", "Income: $amount")
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.expenseFlow.collectLatest { amount ->
                expense = amount.toFloat() ?: 0f
                binding.expenseAmount.setText(amount.toString())
                updateChart(income, expense, savings)
                Log.d("jjj", "Expense: $amount")
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.netSavings.collectLatest { totalAmount ->
                savings = totalAmount.toFloat() ?: 0f
                binding.savingsAmount.setText(totalAmount.toString())
                updateChart(income, expense, savings)
                Log.d("jjj", "Net Saving: $totalAmount")
            }
        }
    }

    private fun updateChart(income: Float, expense: Float, savings: Float) {
        val entries = listOf(
            BarEntry(0f, income),
            BarEntry(1f, expense),
            BarEntry(2f, savings)
        )

        val dataSet = BarDataSet(entries, "Monthly Summary").apply {
            setColors(
                listOf(
                    ContextCompat.getColor(requireContext(), R.color.my_green),
                    ContextCompat.getColor(requireContext(), R.color.my_red),
                    ContextCompat.getColor(requireContext(), R.color.my_blue)
                )
            )
            valueTextSize = 14f
        }

        val barData = BarData(dataSet)
        barData.barWidth = 0.5f // Optional: width of each bar
        binding.barChart.data = barData

        val xAxis = binding.barChart.xAxis
        xAxis.valueFormatter = IndexAxisValueFormatter(listOf("Income", "Expense", "Savings"))
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.granularity = 1f
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(true)
        xAxis.labelRotationAngle = 0f

        binding.barChart.axisLeft.granularity = 1f
        binding.barChart.axisRight.isEnabled = false
        binding.barChart.description.isEnabled = false

        binding.barChart.setFitBars(true)
        binding.barChart.animateY(800)
        binding.barChart.invalidate()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
