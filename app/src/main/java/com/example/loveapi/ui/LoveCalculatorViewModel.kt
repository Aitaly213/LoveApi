package com.example.loveapi.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loveapi.data.LoveApiService
import com.example.loveapi.data.LoveModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

    class LoveCalculatorViewModel(private val api: LoveApiService) : ViewModel() {

    private val _loveModel = MutableLiveData<LoveModel>()
    val loveModel: LiveData<LoveModel> get() = _loveModel

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun calculateLovePercentage(key: String, host: String, firstName: String, secondName: String) {
        api.getPercentage(key, host, firstName, secondName).enqueue(object : Callback<LoveModel> {
            override fun onResponse(call: Call<LoveModel>, response: Response<LoveModel>) {
                if (response.isSuccessful && response.body() != null) {
                    _loveModel.value = response.body()
                } else {
                    _error.value = "Error: ${response.code()}"
                }
            }

            override fun onFailure(call: Call<LoveModel>, t: Throwable) {
                _error.value = t.message.toString()
            }
        })
    }
}