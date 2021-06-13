package com.arulvakku.lyrics.app.ui.view.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.arulvakku.lyrics.app.data.model.AppSetting
import com.arulvakku.lyrics.app.ui.view.MainActivity
import com.arulvakku.lyrics.app.ui.view.download.CacheActivity
import com.arulvakku.lyrics.app.ui.viewmodels.DatabaseViewModel
import com.arulvakku.lyrics.app.utilities.NetworkHelper
import com.arulvakku.lyrics.app.utilities.Status
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "SplashActivity"
    }

    private val viewModel: DatabaseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_splash)

        if (NetworkHelper(this).isNetworkConnected()) {
            checkAppStatus()
        } else {
            subscribe()
        }
    }

    private fun checkAppStatus() {
        val database = Firebase.database
        val myRef = database.getReference("settings")
        // Read from the database
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val settings = dataSnapshot.getValue(AppSetting::class.java)
                if (settings != null) {
                    if (settings.isOpen) {
                        subscribe()
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
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