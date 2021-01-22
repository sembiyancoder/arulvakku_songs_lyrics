package com.arulvakku.lyrics.app.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.arulvakku.lyrics.app.R
import com.arulvakku.lyrics.app.activities.home.HomeActivity
import com.arulvakku.lyrics.app.utilities.Constants
import com.arulvakku.lyrics.app.utilities.Prefs
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.ActivityResult
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.firebase.iid.FirebaseInstanceId

class SplashActivity : AppCompatActivity() {

    private lateinit var appUpdateManager: AppUpdateManager

    companion object {
        private const val REQUEST_CODE_IMMEDIATE_UPDATE = 17362
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        enableTheme()
        if (checkGooglePlayServices()) {
            checkForUpdate();
            getFCMToken()
        } else {
            startMainActivity()
        }
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
        startActivity(intent)
        finish()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_IMMEDIATE_UPDATE) {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    startMainActivity()
                }
                Activity.RESULT_CANCELED -> {
                    startMainActivity()
                }
                ActivityResult.RESULT_IN_APP_UPDATE_FAILED -> {
                    startMainActivity()
                }
            }
        }
    }

    private fun getFCMToken() {
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w(SplashActivity.toString(), "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }

                val token = task.result?.token
                Log.i("token", "Message :: $token")
            })
    }


    private fun checkGooglePlayServices(): Boolean {
        val status = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this)
        return status == ConnectionResult.SUCCESS
    }

    private fun checkForUpdate() {
        appUpdateManager = AppUpdateManagerFactory.create(this)
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo
        // Checks that the platform will allow the specified type of update.
        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                // For a flexible update, use AppUpdateType.FLEXIBLE
                && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
            ) {
                appUpdateManager.startUpdateFlowForResult(
                    // Pass the intent that is returned by 'getAppUpdateInfo()'.
                    appUpdateInfo,
                    // Or 'AppUpdateType.FLEXIBLE' for flexible updates.
                    AppUpdateType.IMMEDIATE,
                    // The current activity making the update request.
                    this,
                    // Include a request code to later monitor this update request.
                    REQUEST_CODE_IMMEDIATE_UPDATE
                )
            } else if (appUpdateInfo.installStatus() == InstallStatus.INSTALLED) {
                startMainActivity()
            } else {
                startMainActivity()
            }
        }
    }

}





