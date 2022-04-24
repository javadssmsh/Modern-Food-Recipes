package com.example.modernfoodrecipes

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        
        com.example.connectivitymanagermodule.CheckConnectivityModule.initialize(applicationContext)
    }
}