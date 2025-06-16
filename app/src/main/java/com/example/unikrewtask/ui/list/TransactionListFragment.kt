package com.example.unikrewtask.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.unikrewtask.databinding.FragmentTransactionListBinding

class TransactionListFragment : Fragment() {
    private var _binding: FragmentTransactionListBinding? = null

    private val binding: FragmentTransactionListBinding? get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTransactionListBinding.inflate(inflater, container, false)
        return _binding?.root    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}