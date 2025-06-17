package com.example.unikrewtask.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.unikrewtask.R
import com.example.unikrewtask.databinding.FragmentAddTransactionBinding
import com.example.unikrewtask.databinding.FragmentSplashBinding
import com.example.unikrewtask.databinding.FragmentTransactionListBinding

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null

    private val binding: FragmentSplashBinding? get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return _binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_addTransactionFragment)
        }, 3000)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}