package com.example.loveapi.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.loveapi.App
import com.example.loveapi.data.LoveCalculatorModel
import com.example.loveapi.data.LoveModel
import com.example.loveapi.databinding.FragmentHomeBinding
import com.example.loveapi.presenter.LoveCalculatorPresenter
import com.example.loveapi.presenter.LoveCalculatorView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment(),LoveCalculatorView {

    private val binding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    private lateinit var presenter: LoveCalculatorPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val api = App().api
        presenter = LoveCalculatorPresenter(this,api)

        binding.btnCalculate.setOnClickListener {
            val firstName = binding.etFirstName.text.toString()
            val secondName = binding.etSecondName.text.toString()
            presenter.onCalculateButtonClicked(
                key = "13db8c0c9fmsh0e8b65404615b3ap1035a5jsn85bfe5faab5c",
                host = "love-calculator.p.rapidapi.com",
                firstName = firstName,
                secondName = secondName
            )
        }

//        binding.btnCalculate.setOnClickListener {
//            App().api.getPercentage(
//                key = "13db8c0c9fmsh0e8b65404615b3ap1035a5jsn85bfe5faab5c",
//                host = "love-calculator.p.rapidapi.com",
//                firstName = binding.etFirstName.text.toString(),
//                secondName = binding.etSecondName.text.toString()
//            ).enqueue(object : Callback<LoveModel> {
//                override fun onResponse(call: Call<LoveModel>, response: Response<LoveModel>) {
//                    if (response.code() in 200..300 && response.body() != null) {
//
//                        findNavController().navigate(
//                            HomeFragmentDirections.actionHomeFragmentToResultFragment(
//                                loverFirstName = response.body()!!.firstName,
//                                loverSecondName = response.body()!!.secondName,
//                                lovePercentage = response.body()!!.percentage,
//                                loveResult = response.body()!!.result
//                            )
//                        )
//                    }
//                }
//
//                override fun onFailure(call: Call<LoveModel>, t: Throwable) {
//                    Toast.makeText(requireContext(), t.message.toString(), Toast.LENGTH_SHORT)
//                        .show()
//                }
//            }
//            )
//        }
    }

    override fun showResult(
        firstName: String,
        secondName: String,
        percentage: String,
        result: String
    ) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToResultFragment(
                loverFirstName = firstName,
                loverSecondName = secondName,
                lovePercentage = percentage,
                loveResult = result
            )
        )
    }

    override fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

}