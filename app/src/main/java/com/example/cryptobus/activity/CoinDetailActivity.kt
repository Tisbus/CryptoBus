package com.example.cryptobus.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cryptobus.R
import com.example.cryptobus.databinding.ActivityCoinDetailBinding
import com.example.cryptobus.viewmodel.CryptoViewModel
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bind: ActivityCoinDetailBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_coin_detail)
        var viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(application)
        )[CryptoViewModel::class.java]
        if(!intent.hasExtra("currency")){
            finish()
            return
        }
        val symbol = intent.getStringExtra("currency")
        symbol?.let { it ->
            viewModel.getDetailInfo(it).observe(this, Observer {
                Picasso.get().load(it.getFullImgUrl()).into(bind.tvLogo)
                bind.tvPrice.text = it.PRICE.toString()
                bind.tvTitle.text = String.format(this.resources.getString(R.string.symbol), it.FROMSYMBOL)
            })
        }

    }


}