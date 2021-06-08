package com.arulvakku.lyrics.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.DebugTree

@HiltAndroidApp
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(DebugTree())
    }
}