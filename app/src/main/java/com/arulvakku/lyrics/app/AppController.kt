package com.arulvakku.lyrics.app

import android.app.Application
import android.content.ContextWrapper
import com.arulvakku.lyrics.app.database.AppDatabase
import com.arulvakku.lyrics.app.utilities.Prefs


class AppController : Application() {

    val database by lazy { AppDatabase.getDatabase(this)
    }

    //  private var mFirebaseAnalytics: FirebaseAnalytics? = null
    override fun onCreate() {
        super.onCreate()

        //  mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        //need to change
        Prefs.Builder()
            .setContext(this)
            .setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName(packageName)
            .setUseDefaultSharedPreference(true)
            .build()
    }
}