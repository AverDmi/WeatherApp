package com.dimthomas.weatherapp

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.dimthomas.weatherapp.business.model.DailyWeatherModel
import com.dimthomas.weatherapp.business.model.HourlyWeatherModel
import com.dimthomas.weatherapp.business.model.WeatherData
import com.dimthomas.weatherapp.databinding.ActivityMainBinding
import com.dimthomas.weatherapp.presenters.MainPresenter
import com.dimthomas.weatherapp.view.MainView
import com.dimthomas.weatherapp.view.adapters.MainDailyListAdapter
import com.dimthomas.weatherapp.view.adapters.MainHourlyListAdapter
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

const val GEO_LOCATION_REQUEST_COD_SUCCESS = 1
const val TAG = "GEO_TEST"
class MainActivity : MvpAppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding

    private val mainPresenter by moxyPresenter { MainPresenter() }

    private val geoService by lazy { LocationServices.getFusedLocationProviderClient(this) }
    private val locationRequest by lazy { initLocationRequest() }
    private lateinit var mLocation: Location
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        checkPermission()
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

        mainPresenter.enable()

        geoService.requestLocationUpdates(locationRequest, geoCallback, null)
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

    // -------------   moxy code   -------------

    override fun displayLocation(data: String) {
        binding.mainCityNameTv.text = data
    }

    override fun displayCurrentData(data: WeatherData) {
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

    override fun displayHourlyData(data: List<HourlyWeatherModel>) {
        (binding.mainHourlyList.adapter as MainHourlyListAdapter).updateData(data)
    }

    override fun displayDailyData(data: List<DailyWeatherModel>) {
        (binding.mainDailyList.adapter as MainDailyListAdapter).updateData(data)
    }

    override fun displayError(error: Throwable) {

    }

    override fun setLoading(flag: Boolean) {

    }

    // -------------   moxy code   -------------

    // -------------   location code   -------------

    private fun initLocationRequest(): LocationRequest {
        return LocationRequest.Builder(PRIORITY_HIGH_ACCURACY, 10000)
            .setMinUpdateIntervalMillis(5000)
            .build()
    }

    private val geoCallback = object : LocationCallback() {
        override fun onLocationResult(geo: LocationResult) {
            Log.d(TAG, "onLocationResult: ${geo.locations.size}")
            for (location in geo.locations) {
                mLocation = location
                mainPresenter.refresh(mLocation.latitude.toString(), mLocation.longitude.toString())
                Log.d(TAG, "onLocationResult: lat: ${location.latitude} ; lon: ${location.longitude}")
            }
        }
    }

    // ------ initial activity code
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        Log.d(TAG, "onRequestPermissionsResult: $requestCode")
    }

    private fun checkPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
            PackageManager. PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED) {
            MaterialAlertDialogBuilder(this)
                .setTitle("Нам нужны гео данные")
                .setMessage("Пожалуйста разрешите доступ к Вашим гео данным для работы приложения")
                .setPositiveButton("Ok") { _,_ ->
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        GEO_LOCATION_REQUEST_COD_SUCCESS)
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                        GEO_LOCATION_REQUEST_COD_SUCCESS)
                }
                .setNegativeButton("Cancel") { dialog,_ -> dialog.dismiss() }
                .create()
                .show()
        }
    }

    // -------------   location code   -------------
}