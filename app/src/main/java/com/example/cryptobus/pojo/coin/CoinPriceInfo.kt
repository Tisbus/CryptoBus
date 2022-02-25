package com.example.cryptobus.pojo.coin

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cryptobus.services.ApiFactory.BASE_IMG_URL
import com.example.cryptobus.utils.convertTimestampToTime
import com.google.gson.annotations.SerializedName
@Entity(tableName = "full_price_list")
data class CoinPriceInfo(

    @SerializedName("TYPE") val TYPE: String? = null,
    @SerializedName("MARKET") val MARKET: String? = null,
    @PrimaryKey
    @SerializedName("FROMSYMBOL") val FROMSYMBOL: String,
    @SerializedName("TOSYMBOL") val TOSYMBOL: String? = null,
    @SerializedName("FLAGS") val FLAGS: String? = null,
    @SerializedName("PRICE") val PRICE: Double? = null,
    @SerializedName("LASTUPDATE") val LASTUPDATE: Long? = null,
    @SerializedName("MEDIAN") val MEDIAN: Double? = null,
    @SerializedName("LASTVOLUME") val LASTVOLUME: Double? = null,
    @SerializedName("LASTVOLUMETO") val LASTVOLUMETO: Double? = null,
    @SerializedName("LASTTRADEID") val LASTTRADEID: String? = null,
    @SerializedName("VOLUMEDAY") val VOLUMEDAY: Double? = null,
    @SerializedName("VOLUMEDAYTO") val VOLUMEDAYTO: Double? = null,
    @SerializedName("VOLUME24HOUR") val VOLUME24HOUR: Double? = null,
    @SerializedName("VOLUME24HOURTO") val VOLUME24HOURTO: Double? = null,
    @SerializedName("OPENDAY") val OPENDAY: Double? = null,
    @SerializedName("HIGHDAY") val HIGHDAY: Double? = null,
    @SerializedName("LOWDAY") val LOWDAY: Double? = null,
    @SerializedName("OPEN24HOUR") val OPEN24HOUR: Double? = null,
    @SerializedName("HIGH24HOUR") val HIGH24HOUR: Double? = null,
    @SerializedName("LOW24HOUR") val LOW24HOUR: Double? = null,
    @SerializedName("LASTMARKET") val LASTMARKET: String? = null,
    @SerializedName("VOLUMEHOUR") val VOLUMEHOUR: Double? = null,
    @SerializedName("VOLUMEHOURTO") val VOLUMEHOURTO: Double? = null,
    @SerializedName("OPENHOUR") val OPENHOUR: Double? = null,
    @SerializedName("HIGHHOUR") val HIGHHOUR: Double? = null,
    @SerializedName("LOWHOUR") val LOWHOUR: Double? = null,
    @SerializedName("TOPTIERVOLUME24HOUR") val TOPTIERVOLUME24HOUR: Double? = null,
    @SerializedName("TOPTIERVOLUME24HOURTO") val TOPTIERVOLUME24HOURTO: Double? = null,
    @SerializedName("CHANGE24HOUR") val CHANGE24HOUR: Double? = null,
    @SerializedName("CHANGEPCT24HOUR") val CHANGEPCT24HOUR: Double? = null,
    @SerializedName("CHANGEDAY") val CHANGEDAY: Double? = null,
    @SerializedName("CHANGEPCTDAY") val CHANGEPCTDAY: Double? = null,
    @SerializedName("CHANGEHOUR") val CHANGEHOUR: Double? = null,
    @SerializedName("CHANGEPCTHOUR") val CHANGEPCTHOUR: Double? = null,
    @SerializedName("CONVERSIONTYPE") val CONVERSIONTYPE: String? = null,
    @SerializedName("CONVERSIONSYMBOL") val CONVERSIONSYMBOL: String? = null,
    @SerializedName("SUPPLY") val SUPPLY: Int? = null,
    @SerializedName("MKTCAP") val MKTCAP: Double? = null,
    @SerializedName("MKTCAPPENALTY") val MKTCAPPENALTY: Int? = null,
    @SerializedName("CIRCULATINGSUPPLY") val CIRCULATINGSUPPLY: Int? = null,
    @SerializedName("CIRCULATINGSUPPLYMKTCAP") val CIRCULATINGSUPPLYMKTCAP: Double? = null,
    @SerializedName("TOTALVOLUME24H") val TOTALVOLUME24H: Double? = null,
    @SerializedName("TOTALVOLUME24HTO") val TOTALVOLUME24HTO: Double? = null,
    @SerializedName("TOTALTOPTIERVOLUME24H") val TOTALTOPTIERVOLUME24H: Double? = null,
    @SerializedName("TOTALTOPTIERVOLUME24HTO") val TOTALTOPTIERVOLUME24HTO: Double? = null,
    @SerializedName("IMAGEURL") val IMAGEURL: String? = null

)

{
    fun getFormattedTime() : String{
        return convertTimestampToTime(LASTUPDATE)
    }

    fun getFullImgUrl() : String{
        return BASE_IMG_URL + IMAGEURL
    }
}