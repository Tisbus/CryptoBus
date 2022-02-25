package com.example.cryptobus.services

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {
    private const val BASE_URL = "https://min-api.cryptocompare.com/data/"
    const val BASE_IMG_URL = "https://www.cryptocompare.com/"
    private val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(BASE_URL)
        .build()
    val apiService : ApiService = retrofit.create(ApiService::class.java)

}