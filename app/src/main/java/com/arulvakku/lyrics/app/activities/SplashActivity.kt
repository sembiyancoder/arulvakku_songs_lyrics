package com.arulvakku.lyrics.app.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.arulvakku.lyrics.app.R
import com.arulvakku.lyrics.app.activities.home.HomeActivity
import com.arulvakku.lyrics.app.utilities.Constants
import com.arulvakku.lyrics.app.utilities.Prefs

import java.lang.Thread.sleep

class SplashActivity : AppCompatActivity() {

    var splashTime: Long =  1000;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        enableTheme()

        startMainActivity()
    }

    private fun enableTheme() {
        val theme = Prefs.getString(Constants.SP_KEYS.THEME, "")
        if (theme == "Light") {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else if (theme == "Dark") {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }


    private fun startMainActivity() {
        val intent = Intent(this, HomeActivity::class.java)

        //showing splash screen 3 seconds  only for the fist time then 1 seconds
        splashTime = if (Prefs.getBoolean(Constants.IS_FIRST_TIME,true)){
            3000
        } else {
            500
        }


            Thread {
                try {
                    sleep(splashTime)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    startActivity(intent)
                    finish()
                }
            }.start()
    }

}



