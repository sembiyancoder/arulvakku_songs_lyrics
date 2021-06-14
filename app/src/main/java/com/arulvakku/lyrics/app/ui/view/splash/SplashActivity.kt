package com.arulvakku.lyrics.app.ui.view.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.arulvakku.lyrics.app.data.model.AppSetting
import com.arulvakku.lyrics.app.ui.view.MainActivity
import com.arulvakku.lyrics.app.ui.view.download.CacheActivity
import com.arulvakku.lyrics.app.ui.viewmodels.DatabaseViewModel
import com.arulvakku.lyrics.app.utilities.ConnectionType
import com.arulvakku.lyrics.app.utilities.NetworkMonitorUtil
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
    private val networkMonitor = NetworkMonitorUtil(this)
    private var isNetworkAvailable: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*    if (NetworkHelper(this).isNetworkConnected()) {
                checkAppStatus()
            } else {
                subscribe()
            }*/
        networkMonitor.result = { isAvailable, type ->
            runOnUiThread {
                when (isAvailable) {
                    true -> {
                        when (type) {
                            ConnectionType.Wifi -> {
                                Log.d(TAG, "Wifi Connection")
                                if (!isNetworkAvailable) {
                                    isNetworkAvailable = true
                                    subscribe()
                                }
                            }
                            ConnectionType.Cellular -> {
                                Log.d(TAG, "Cellular Connection")
                                if (!isNetworkAvailable) {
                                    isNetworkAvailable = true
                                    subscribe()
                                }
                            }
                            else -> {
                            }
                        }
                    }
                    false -> {
                        Log.d(TAG, "No Connection")
                        isNetworkAvailable = false
                        subscribe()
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        networkMonitor.register()
    }


    override fun onStop() {
        super.onStop()
        networkMonitor.unregister()
    }


    private fun checkAppStatus(status: Boolean) {
        val database = Firebase.database
        val myRef = database.getReference("settings")
        // Read from the database
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.d(TAG,dataSnapshot.toString())
                val settings = dataSnapshot.getValue(AppSetting::class.java)
                startScreen(status)
                if (settings != null) {
                    /*if (settings.isOpen) {
                        startScreen(status)
                    }*/
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
                            checkAppStatus(true)
                        } else {
                            if (isNetworkAvailable) {
                                startIntentActivity(false)
                                checkAppStatus(false)
                            } else {
                                Toast.makeText(
                                    this,
                                    "No Connection. Please enable internet connection",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    } ?: if (isNetworkAvailable) {
                        checkAppStatus(false)
                    } else {
                        Toast.makeText(
                            this,
                            "No Connection. Please enable internet connection",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                Status.ERROR -> {
                    startIntentActivity(false)
                    Timber.d("error: ${it.message}")
                }
            }
        }
    }


    private fun checkForNetworkConnection() {
        networkMonitor.result = { isAvailable, type ->
            runOnUiThread {
                when (isAvailable) {
                    true -> {
                        when (type) {
                            ConnectionType.Wifi -> {
                                Log.d(TAG, "Wifi Connection")
                                startIntentActivity(true)
                            }
                            ConnectionType.Cellular -> {
                                Log.d(TAG, "Cellular Connection")
                                startIntentActivity(true)
                            }
                            else -> {
                            }
                        }
                    }
                    false -> {
                        Log.d(TAG, "No Connection")
                        Toast.makeText(
                            this,
                            "No Connection. Please enable internet connection",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun startIntentActivity(startMainActivity: Boolean) {
        checkAppStatus(startMainActivity)
    }

    private fun startScreen(startMainActivity: Boolean) {

        val intent: Intent = if (startMainActivity) {
            Intent(this, MainActivity::class.java)
        } else {
            Intent(this, CacheActivity::class.java)
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent)
    }
}