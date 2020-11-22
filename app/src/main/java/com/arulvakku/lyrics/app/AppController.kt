package com.arulvakku.lyrics.app

import android.app.Application
import android.content.ContextWrapper
import com.arulvakku.lyrics.app.utilities.CustomPreferences
import com.arulvakku.lyrics.app.utilities.Prefs

class AppController : Application() {

    companion object {
        var CURRENT_CLASS_NAME = ""
    }

    override fun onCreate() {
        super.onCreate()
        CustomPreferences.init(applicationContext);

        //need to change
        Prefs.Builder()
            .setContext(this)
            .setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName(packageName)
            .setUseDefaultSharedPreference(true)
            .build()
    }
}