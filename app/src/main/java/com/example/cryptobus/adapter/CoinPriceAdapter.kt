package com.example.cryptobus.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptobus.R
import com.example.cryptobus.databinding.ItemCoinInfoBinding
import com.example.cryptobus.pojo.coin.CoinPriceInfo
import com.squareup.picasso.Picasso

class CoinPriceAdapter(private val context : Context, var priceList : List<CoinPriceInfo>) : RecyclerView.Adapter<CoinPriceAdapter.CoinPriceViewHolder>() {

    lateinit var onCoinClickListener : OnCoinClickListener

    inner class CoinPriceViewHolder(val binding : ItemCoinInfoBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinPriceViewHolder {
        val binding = ItemCoinInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinPriceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinPriceViewHolder, position: Int) {
        with(holder){
            with(priceList[position]){
                binding.tvSellPrice.text = this.PRICE.toString()
                binding.tvSymbols.text = String.format(context.resources.getString(R.string.symbols), this.FROMSYMBOL, this.TOSYMBOL)
                binding.tvUpdateCurrency.text = String.format(context.resources.getString(R.string.last_update), this.getFormattedTime())
                Picasso.get().load(this.getFullImgUrl()).into(binding.tvCurrencyLogo)
                itemView.setOnClickListener {
                    onCoinClickListener.onCoinClick(this)
                }
            }
        }
    }

    interface OnCoinClickListener{
        fun onCoinClick(coin : CoinPriceInfo)
    }

    override fun getItemCount(): Int {
        return priceList.size
    }

}