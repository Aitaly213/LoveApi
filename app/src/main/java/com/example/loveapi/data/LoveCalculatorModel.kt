package com.example.loveapi.data

import retrofit2.Callback

class LoveCalculatorModel (private val api: LoveApiService) {
    fun calculateLovePercentage(
        key: String,
        host: String,
        firstName: String,
        secondName: String,
        callback: Callback<LoveModel>
    ) {
        api.getPercentage(key, host, firstName, secondName).enqueue(callback)
    }
}