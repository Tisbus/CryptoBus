package com.example.cryptobus.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cryptobus.pojo.coin.CoinPriceInfo

@Dao
interface CryptoDao {
    @Query("Select * from full_price_list Order by PRICE DESC")
    fun getPriceList(): LiveData<List<CoinPriceInfo>>

    @Query("Select * from full_price_list where FROMSYMBOL == :fSym Limit 1")
    fun getPriceInfoAboutCoin(fSym : String):LiveData<CoinPriceInfo>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPriceList(priceList : List<CoinPriceInfo>)
    @Query("Delete from full_price_list")
    fun deleteAllPrice()
}