package com.dimthomas.weatherapp.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.dimthomas.weatherapp.R
import com.dimthomas.weatherapp.business.model.HourlyWeatherModel
import com.dimthomas.weatherapp.databinding.ItemMainHourlyBinding

class MainHourlyListAdapter : BaseAdapter<HourlyWeatherModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyViewHolder {
        val itemBinding = ItemMainHourlyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HourlyViewHolder(itemBinding)
    }

    inner class HourlyViewHolder(private val itemBinding: ItemMainHourlyBinding) : BaseViewHolder(itemBinding.root) {
        override fun bindView(position: Int) {
            with(itemBinding) {
                itemHourlyTimeTv.text = "14:00"
                itemHourlyTempTv.text = "14\u00B0"
                itemHourlyPopTv.text = "100%"
                if (position == 3) {
                itemHourlyTimeTv.setTextColor(ContextCompat.getColor(itemHourlyTimeTv.context, R.color.purple_700))
                }
                itemHourlyWeatherConditionIcon.setImageResource(R.drawable.ic_sun)
            }
        }

    }


}