package com.arulvakku.lyrics.app

import android.app.Application
import android.content.ContextWrapper
import com.arulvakku.lyrics.app.utilities.Prefs

class MyApplication : Application(){


    companion object {
        var CURRENT_CLASS_NAME = ""
    }

    override fun onCreate() {
        super.onCreate()
        // Initialize the Prefs class
        Prefs.Builder()
            .setContext(this)
            .setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName(packageName)
            .setUseDefaultSharedPreference(true)
            .build()

    }
}