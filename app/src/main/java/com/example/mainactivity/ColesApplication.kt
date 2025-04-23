package com.example.mainactivity

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ColesApplication : Application(){
    override fun onCreate() {
        super.onCreate()
    }

}