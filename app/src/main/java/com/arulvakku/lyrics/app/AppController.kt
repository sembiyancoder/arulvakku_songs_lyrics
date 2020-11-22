package com.arulvakku.lyrics.app

import android.app.Application
import com.arulvakku.lyrics.app.utilities.CustomPreferences

class AppController : Application() {
    override fun onCreate() {
        super.onCreate()
        CustomPreferences.init(applicationContext);
    }
}