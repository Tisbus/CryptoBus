package com.example.cryptobus.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.cryptobus.data.Database
import com.example.cryptobus.pojo.coin.CoinPriceInfo
import com.example.cryptobus.pojo.coin.CoinPriceInfoRawData
import com.example.cryptobus.services.ApiFactory
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CryptoViewModel(application: Application) : AndroidViewModel(application) {
    private val db = Database.getInstance(application)
    val priceList = db.cryptoDao().getPriceList()
    private val compositeDisposable = CompositeDisposable()
    init {
        loadDate()
    }
    fun getDetailInfo(fSym : String) : LiveData<CoinPriceInfo>{
        return db.cryptoDao().getPriceInfoAboutCoin(fSym)
    }
    fun deleteAllPriceList(){
        db.cryptoDao().deleteAllPrice()
    }
    private fun loadDate() {
        val disposable = ApiFactory.apiService.getTopCoinInfo(limit = 10)
            .map { it -> it.Data?.map { it.CoinInfo?.Name }?.joinToString(",") }
            .flatMap { ApiFactory.apiService.getFullPriceList(fromSymbols = it) }
            .map { getPriceListFromRawData(it) }
            .delaySubscription(10, TimeUnit.SECONDS)
            .retry()
            .repeat()
            .subscribeOn(Schedulers.io())
            .subscribe({
                db.cryptoDao().insertPriceList(it)
                Log.i("check", it.toString())
            }, {
                Log.i("check", it.message.toString())
            })
        compositeDisposable.add(disposable)
    }

    private fun getPriceListFromRawData(coinPriceInfoRawData: CoinPriceInfoRawData): List<CoinPriceInfo> {
        val result = ArrayList<CoinPriceInfo>()
        val jasonObjects = coinPriceInfoRawData.RAW ?: return result
        val coinKeySet = jasonObjects.keySet()
        for (coinKey in coinKeySet) {
            val currency = jasonObjects.getAsJsonObject(coinKey)
            val currencyKey = currency.keySet()
            for (currencySet in currencyKey) {
                val priceCurrency = Gson().fromJson(
                    currency.getAsJsonObject(currencySet),
                    CoinPriceInfo::class.java
                )
                result.add(priceCurrency)

            }
        }
        return result
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}