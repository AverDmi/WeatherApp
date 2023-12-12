package com.dimthomas.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dimthomas.weatherapp.databinding.ActivityMainBinding
import com.dimthomas.weatherapp.view.adapters.MainDailyListAdapter
import com.dimthomas.weatherapp.view.adapters.MainHourlyListAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()

        binding.mainHourlyList.apply {
            adapter = MainHourlyListAdapter()
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }

        binding.mainDailyList.apply {
            adapter = MainDailyListAdapter()
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }
    }

    private fun initViews() {
        with(binding) {
            mainCityNameTv.text = "Moscow"
            mainDateTv.text = "1 april"
            mainWeatherConditionIcon.setImageResource(R.drawable.ic_sun)
            mainTemp.text = "25\u00B0"
            mainTempMinTv.text = "19"
            mainTempAvgTv.text = "25"
            mainTempMaxTv.text = "30"
            mainWeatherImage.setImageResource(R.mipmap.cloud3x)
            mainPressureMuTv.text = "1023 hPa"
            mainHumidityMuTv.text = "88 %"
            mainWindSpeedMuTv.text = "5 m/s"
            mainSunriseTimeTv.text = "4:30"
            mainSunsetTimeTv.text = "22:43"
        }
    }
}