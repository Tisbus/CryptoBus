package com.example.cryptobus.pojo.main

import com.google.gson.annotations.SerializedName

data class CoinInfo(

    @SerializedName("Id")
    val Id: String? = null,
    @SerializedName("Name")
    val Name: String? = null,
    @SerializedName("FullName")
    val FullName: String? = null,
    @SerializedName("ImageUrl")
    val ImageUrl: String? = null

)