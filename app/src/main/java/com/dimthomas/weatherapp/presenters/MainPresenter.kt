package com.dimthomas.weatherapp.presenters

import com.dimthomas.weatherapp.view.MainView

class MainPresenter: BasePresenter<MainView>() {
    override fun enable() {
        TODO("Not yet implemented")
    }

    fun refresh(lat: String, lon: String) {
        viewState.setLoading(true)
    }
}