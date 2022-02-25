package com.example.cryptobus.pojo.coin

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class CoinPriceInfoRawData(
    @SerializedName("RAW") val RAW: JsonObject? = null
)
