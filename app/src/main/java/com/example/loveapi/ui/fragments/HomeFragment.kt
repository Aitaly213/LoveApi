package com.example.loveapi.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.loveapi.App
import com.example.loveapi.data.LoveModel
import com.example.loveapi.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private val binding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
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
            App().api.getPercentage(
                key = "13db8c0c9fmsh0e8b65404615b3ap1035a5jsn85bfe5faab5c",
                host = "love-calculator.p.rapidapi.com",
                firstName = binding.etFirstName.text.toString(),
                secondName = binding.etSecondName.text.toString()
            ).enqueue(object : Callback<LoveModel> {
                override fun onResponse(call: Call<LoveModel>, response: Response<LoveModel>) {
                    if (response.code() in 200..300 && response.body() != null) {

                        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToResultFragment(
                            loverFirstName = response.body()!!.firstName,
                            loverSecondName = response.body()!!.secondName,
                            lovePercentage = response.body()!!.percentage,
                            loveResult = response.body()!!.result
                        ))
                    }
                }

                override fun onFailure(call: Call<LoveModel>, t: Throwable) {
                    Toast.makeText(requireContext(),t.message.toString(),Toast.LENGTH_SHORT).show()
                }
            }
            )
        }
    }

}