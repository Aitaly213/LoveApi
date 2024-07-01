package com.example.loveapi.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.loveapi.databinding.FragmentResultBinding


class ResultFragment : Fragment() {

    private val binding by lazy {
        FragmentResultBinding.inflate(layoutInflater)
    }

    private val args by navArgs<ResultFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getData()
        setupListeners()
    }

    private fun setupListeners() {
        binding.btnTryAgain.setOnClickListener {
            findNavController().navigate(
                ResultFragmentDirections.actionResultFragmentToHomeFragment()
            )
        }
    }

    private fun getData() {
        binding.tvFirstName.text = args.loverFirstName.toString()
        binding.tvSecondName.text = args.loverSecondName.toString()
        binding.tvLovePercentage.text = "${args.lovePercentage}%"
        binding.tvLoveResult.text = args.loveResult.toString()
    }
}