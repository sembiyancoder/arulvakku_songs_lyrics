package com.arulvakku.lyrics.app.utilities

import android.app.Activity
import android.content.Context

import android.content.SharedPreferences
import android.content.SharedPreferences.Editor


class CustomPreferences {


    companion object {
        private var mSharedPref: SharedPreferences? = null
        private lateinit var prefsEditor: Editor
        fun init(context: Context) {
            if (mSharedPref == null) mSharedPref =
                context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE)
        }
        fun read(key: String?, defValue: String?): String? {
            return mSharedPref!!.getString(key, defValue)
        }

        fun write(key: String?, value: String?) {
            prefsEditor = mSharedPref!!.edit()
            prefsEditor.putString(key, value)
            prefsEditor.apply()
            prefsEditor.commit()
        }

        fun write(key: String?, value: Int) {
            prefsEditor = mSharedPref!!.edit()
            prefsEditor.putInt(key, value)
            prefsEditor.apply()
            prefsEditor.commit()
        }

        fun write(key: String?, value: Float) {
            prefsEditor = mSharedPref!!.edit()
            prefsEditor.putFloat(key, value)
            prefsEditor.apply()
            prefsEditor.commit()
        }

        fun read(key: String?, defValue: Boolean): Boolean {
            return mSharedPref!!.getBoolean(key, defValue)
        }

        fun write(key: String?, value: Boolean) {
            prefsEditor = mSharedPref!!.edit()
            prefsEditor.putBoolean(key, value)
            prefsEditor.apply()
            prefsEditor.commit()
        }

        fun read(key: String?, defValue: Int): Int {
            return mSharedPref!!.getInt(key, defValue)
        }

        fun read(key: String?, defValue: Float): Float {
            return mSharedPref!!.getFloat(key, defValue)
        }

        fun clear() {
            prefsEditor = mSharedPref!!.edit()
            prefsEditor.clear()
            prefsEditor.apply()
            prefsEditor.commit()
        }
    }

}