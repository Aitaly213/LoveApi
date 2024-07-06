package com.example.loveapi.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.loveapi.App
import com.example.loveapi.databinding.FragmentHomeBinding
import com.example.loveapi.ui.LoveCalculatorViewModel

class HomeFragment : Fragment() {

    private val binding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    private val api = App().api

    private val viewModel: LoveCalculatorViewModel by lazy {
        LoveCalculatorViewModel(api)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCalculate.setOnClickListener {
            val firstName = binding.etFirstName.text.toString()
            val secondName = binding.etSecondName.text.toString()
            viewModel.calculateLovePercentage(
                key = "13db8c0c9fmsh0e8b65404615b3ap1035a5jsn85bfe5faab5c",
                host = "love-calculator.p.rapidapi.com",
                firstName = firstName,
                secondName = secondName
            )
        }

        viewModel.loveModel.observe(viewLifecycleOwner, Observer { loveModel ->
            loveModel?.let {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToResultFragment(
                        loverFirstName = it.firstName,
                        loverSecondName = it.secondName,
                        lovePercentage = it.percentage,
                        loveResult = it.result
                    )
                )
            }
        })

        viewModel.error.observe(viewLifecycleOwner, Observer { errorMessage ->
            errorMessage?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        })
    }

}