package com.example.loveapi.presenter

interface LoveCalculatorView {
    fun showResult(firstName: String, secondName: String, percentage: String, result: String)
    fun showError(message: String)
}