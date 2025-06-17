package com.example.unikrewtask.summary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.unikrewtask.R
import com.example.unikrewtask.databinding.FragmentSummaryBinding
import com.example.unikrewtask.databinding.FragmentTransactionListBinding


class SummaryFragment : Fragment() {
    private var _binding: FragmentSummaryBinding? = null

    private val binding: FragmentSummaryBinding? get() = _binding



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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}