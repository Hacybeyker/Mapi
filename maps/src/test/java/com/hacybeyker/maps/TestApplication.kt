package com.hacybeyker.maps

import android.app.Application

class TestApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setTheme(R.style.ThemeOverlay_AppCompat_Dark)
    }
}
