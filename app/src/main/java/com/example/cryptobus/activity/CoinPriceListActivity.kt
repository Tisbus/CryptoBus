package com.example.cryptobus.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cryptobus.R
import com.example.cryptobus.adapter.CoinPriceAdapter
import com.example.cryptobus.databinding.ActivityCoinPriceListBinding
import com.example.cryptobus.pojo.coin.CoinPriceInfo
import com.example.cryptobus.viewmodel.CryptoViewModel


class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var viewModel: CryptoViewModel
    private lateinit var adapter : CoinPriceAdapter
    private lateinit var list : List<CoinPriceInfo>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_price_list)
        val bind: ActivityCoinPriceListBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_coin_price_list)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(application)
        )[CryptoViewModel::class.java]
        /*viewModel.deleteAllPriceList()*/
/*        fun goDetailPage(coin: CoinPriceInfo) : Unit{
            val goDetail = Intent(this, CoinDetailActivity::class.java).apply {
                putExtra("currency", coin.FROMSYMBOL)
            }
            startActivity(goDetail)
        }*/
        viewModel.priceList.observe(this, Observer {
            list = it
            adapter = CoinPriceAdapter(this, list)
            bind.recyclerMain.adapter = adapter
            adapter.onCoinClickListener = object : CoinPriceAdapter.OnCoinClickListener{
                override fun onCoinClick(coin: CoinPriceInfo) {
/*                    val goDetail = Intent(this@CoinPriceListActivity, CoinDetailActivity::class.java).apply {
                        putExtra("currency", coin.FROMSYMBOL)
                    }
                    startActivity(goDetail)*/
                    val goDetail = CoinDetailActivity.newIntent(this@CoinPriceListActivity, coin.FROMSYMBOL)
                    startActivity(goDetail)
                }
            }
        })







/*        viewModel.getDetailInfo("BTC").observe(this, Observer {
            Log.i("check", it.toString())
        })*/

    }

}
