package com.arulvakku.lyrics.app.ui.view.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.arulvakku.lyrics.app.ui.view.MainActivity
import com.arulvakku.lyrics.app.ui.view.download.CacheActivity
import com.arulvakku.lyrics.app.ui.viewmodels.DatabaseViewModel
import com.arulvakku.lyrics.app.utilities.NetworkHelper
import com.arulvakku.lyrics.app.utilities.Status
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val viewModel: DatabaseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_splash)
        subscribe()
    }


    private fun subscribe() {
        viewModel.getSongCategoriesCount() // get count

        viewModel.countResult.observe(this) { it ->
            when (it.status) {
                Status.LOADING -> {
                    Timber.d("loading...")
                }
                Status.SUCCESS -> {
                    Timber.d("success: ${it.data}")
                    it.data?.let {
                        if ((it.categoryCount.toInt() != 0) && (it.songCount.toInt() != 0)) {
                            startIntentActivity(true)
                        } else {
                            startIntentActivity(false)
                        }
                    } ?: startIntentActivity(false)
                }
                Status.ERROR -> {
                    startIntentActivity(false)
                    Timber.d("error: ${it.message}")
                }
            }
        }
    }

    private fun startIntentActivity(startMainActivity: Boolean) {
        val intent: Intent = if (startMainActivity) {
            Intent(this, MainActivity::class.java)
        } else {
            Intent(this, CacheActivity::class.java)
        }
        startActivity(intent)
        finish()
    }
}