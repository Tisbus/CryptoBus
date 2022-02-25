package com.example.cryptobus.services

import com.example.cryptobus.pojo.coin.CoinPriceInfoRawData
import com.example.cryptobus.pojo.main.CryptoMain
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top/totalvolfull")
    fun getTopCoinInfo(
        @Query(PARAM_API_KEY) apiKey : String = API_KEY,
        @Query(PARAM_LIMIT) limit : Int = 10,
        @Query(PARAM_TO_SYMBOL) symbol : String = CURRENCY
    ): Single<CryptoMain>

    @GET("pricemultifull")
    fun getFullPriceList(
        @Query(PARAM_API_KEY) apiKey : String = API_KEY,
        @Query(PARAM_TO_SYMBOLS) toSymbols : String = CURRENCY,
        @Query(PARAM_FROM_SYMBOLS) fromSymbols : String
    ):Single<CoinPriceInfoRawData>

    companion object{
        private const val PARAM_LIMIT = "limit"
        private const val PARAM_TO_SYMBOL = "tsym"
        private const val PARAM_TO_SYMBOLS = "tsyms"
        private const val PARAM_FROM_SYMBOLS = "fsyms"
        private const val PARAM_API_KEY = "api_key"
        private const val CURRENCY = "USD"
        private const val API_KEY = "931af154e9d4c6ac2d3bc747f08d94f195a771cb919e62bd09e6fa60a839ea94"
    }
}