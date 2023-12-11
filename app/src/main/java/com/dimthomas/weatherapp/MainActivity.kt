package com.dimthomas.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dimthomas.weatherapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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