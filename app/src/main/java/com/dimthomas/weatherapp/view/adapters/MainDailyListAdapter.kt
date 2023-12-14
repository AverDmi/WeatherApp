package com.dimthomas.weatherapp.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dimthomas.weatherapp.R
import com.dimthomas.weatherapp.business.model.DailyWeatherModel
import com.dimthomas.weatherapp.databinding.ItemMainDailyBinding

class MainDailyListAdapter: BaseAdapter<DailyWeatherModel>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        val itemBinding = ItemMainDailyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DailyViewHolder(itemBinding)
    }

    inner class DailyViewHolder(private val itemBinding: ItemMainDailyBinding): BaseViewHolder(itemBinding.root) {
        override fun bindView(position: Int) {
            with(itemBinding) {
                itemDailyDateTv.text = "25 Saturday"
                itemDailyPopTv.text = "25 %"
                itemDailyMinTempTv.text= "12°"
                itemDailyMaxTempTv.text= "18°"
                itemDailyWeatherConditionIcon.setImageResource(R.drawable.ic_sun)
            }
        }

    }
}