package com.example.cryptobus.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cryptobus.pojo.coin.CoinPriceInfo

@androidx.room.Database(entities = [CoinPriceInfo::class], version = 2, exportSchema = false)
abstract class Database : RoomDatabase() {
    companion object{
        private var database : Database? = null
        private const val DB_NAME : String = "crypto.db"
        private val LOCK = Any()

        fun getInstance(context : Context) : Database {
            synchronized(LOCK){
                database?.let { return it }
                val instanceDb = Room.databaseBuilder(
                    context,
                    Database::class.java,
                    DB_NAME
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                database = instanceDb
                return instanceDb
            }

        }
    }
    abstract fun cryptoDao() : CryptoDao

}