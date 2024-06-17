package com.example.loveapi.data

import com.google.gson.annotations.SerializedName

class LoveModel (

    @SerializedName("fname")
    val firstName: String,
    @SerializedName("sname")
    val secondName: String,
    @SerializedName("percentage")
    val percentage: String,
    @SerializedName("result")
    val result: String,

)