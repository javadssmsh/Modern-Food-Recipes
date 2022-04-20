package com.example.modernfoodrecipes

import android.app.Application
import android.util.Log
import com.example.modernfoodrecipes.util.CheckConnectivityModule
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        CheckConnectivityModule.initialize(applicationContext)

    }
}