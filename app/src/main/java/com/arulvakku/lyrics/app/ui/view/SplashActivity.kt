package com.arulvakku.lyrics.app.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arulvakku.lyrics.app.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        startCacheActivity();
    }

    private fun startCacheActivity() {
        val intent = Intent(this, CacheActivity::class.java)
        startActivity(intent)
        finish()
    }
}