package com.example.cryptobus.pojo.main

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("CoinInfo") val CoinInfo: CoinInfo? = CoinInfo()
)