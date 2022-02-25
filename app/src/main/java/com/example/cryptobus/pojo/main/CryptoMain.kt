package com.example.cryptobus.pojo.main

import com.google.gson.annotations.SerializedName

data class CryptoMain(
    @SerializedName("Data") val Data: MutableList<Data>? = null
)