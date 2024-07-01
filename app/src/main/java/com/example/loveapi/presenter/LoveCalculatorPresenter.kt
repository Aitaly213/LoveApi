package com.example.loveapi.presenter

import com.example.loveapi.data.LoveApiService
import com.example.loveapi.data.LoveCalculatorModel
import com.example.loveapi.data.LoveModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoveCalculatorPresenter (private val view: LoveCalculatorView, private val api: LoveApiService) {

    private val model= LoveCalculatorModel(api)

    fun onCalculateButtonClicked(key: String, host: String, firstName: String, secondName: String) {
        model.calculateLovePercentage(key, host, firstName, secondName, object :
            Callback<LoveModel> {
            override fun onResponse(call: Call<LoveModel>, response: Response<LoveModel>) {
                if (response.isSuccessful && response.body() != null) {
                    val loveModel = response.body()!!
                    view.showResult(loveModel.firstName, loveModel.secondName, loveModel.percentage, loveModel.result)
                } else {
                    view.showError("Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<LoveModel>, t: Throwable) {
                view.showError(t.message.toString())
            }
        })
    }
}