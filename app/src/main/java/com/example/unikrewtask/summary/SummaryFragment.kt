package com.example.unikrewtask.summary

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.unikrewtask.R
import com.example.unikrewtask.databinding.FragmentSummaryBinding
import com.example.unikrewtask.databinding.FragmentTransactionListBinding
import com.example.unikrewtask.ui.add.AddTransViewModel


class SummaryFragment : Fragment() {
    private var _binding: FragmentSummaryBinding? = null

    private val binding: FragmentSummaryBinding? get() = _binding
    private lateinit var viewModel: AddTransViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSummaryBinding.inflate(inflater, container, false)
        return _binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel=ViewModelProvider(this).get(AddTransViewModel::class.java)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}