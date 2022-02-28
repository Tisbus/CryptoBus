package com.example.cryptobus.activity

import android.content.Context
import android.content.Intent
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
        if(!intent.hasExtra(FROM_SYMBOL)){
            finish()
            return
        }
        val symbol = intent.getStringExtra(FROM_SYMBOL)
        symbol?.let { it ->
            viewModel.getDetailInfo(it).observe(this, Observer {
                Picasso.get().load(it.getFullImgUrl()).into(bind.tvLogo)
                bind.tvPrice.text = String.format(this.resources.getString(R.string.price), it.PRICE.toString())
                bind.tvTitle.text = String.format(this.resources.getString(R.string.symbols), it.TOSYMBOL, it.FROMSYMBOL)
                bind.tvMinOrder.text = String.format(this.resources.getString(R.string.price_min), it.LOWDAY.toString())
                bind.tvMaxOrder.text = String.format(this.resources.getString(R.string.price_max),it.HIGHDAY.toString())
                bind.tvLastUpdateOrder.text = String.format(this.resources.getString(R.string.last_order),it.LASTMARKET)
                bind.tvUpdateDate.text = String.format(this.resources.getString(R.string.last_update_order), it.getFormattedTime())
            })
        }

    }
    companion object{
        private const val FROM_SYMBOL : String = "fSym"

        fun newIntent(context : Context, name : String) : Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(FROM_SYMBOL, name)
            return intent
        }
    }


}