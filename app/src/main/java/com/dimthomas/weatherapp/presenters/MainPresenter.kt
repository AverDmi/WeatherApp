package com.dimthomas.weatherapp.presenters

import com.dimthomas.weatherapp.view.MainView

class MainPresenter: BasePresenter<MainView>() {
    override fun enable() {
    }

    fun refresh(lat: String, lon: String) {
        viewState.setLoading(true)
    }
}