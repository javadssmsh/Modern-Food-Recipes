package com.example.modernfoodrecipes

import android.app.Application
import android.util.Log
import androidx.lifecycle.Observer
import com.example.modernfoodrecipes.util.ConnectionLiveData
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val connectionLiveData = ConnectionLiveData(applicationContext)
        connectionLiveData.observeForever {
            Log.d("observeForever", "connectionLiveData state  :  $it")
        }
    }
}