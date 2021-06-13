package com.arulvakku.lyrics.app

import android.app.Application
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.DebugTree

@HiltAndroidApp
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        //Enable collection for select users by calling the Crashlytics data collection override at runtime.
        //FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(false)
        Timber.plant(DebugTree())
    }
}