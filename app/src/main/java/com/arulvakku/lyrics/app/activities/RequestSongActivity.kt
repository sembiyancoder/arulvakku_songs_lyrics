package com.arulvakku.lyrics.app.activities

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.arulvakku.lyrics.app.R
import com.creativityapps.gmailbackgroundlibrary.BackgroundMail
import com.creativityapps.gmailbackgroundlibrary.BackgroundMail.OnFailCallback
import com.creativityapps.gmailbackgroundlibrary.BackgroundMail.OnSuccessCallback
import kotlinx.android.synthetic.main.activity_request_song.*


class RequestSongActivity : AppCompatActivity() {

    var userName: String = "";
    lateinit var context: Context;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_song)
        context = this@RequestSongActivity
        btnSend.setOnClickListener {
            if (!edtSongRequested.text.toString().trim().equals("")) {
                BackgroundMail.newBuilder(this)
                    .withUsername("sembiyanteam@gmail.com")
                    .withPassword("Jl29ients**")
                    .withMailto("arulvakkudevteam@gmail.com")
                    .withType(BackgroundMail.TYPE_PLAIN)
                    .withSubject("Request for songs")
                    .withBody(edtSongRequested.text.toString().trim())
                    .withOnSuccessCallback(OnSuccessCallback {
                    })
                    .withOnFailCallback(OnFailCallback {
                    })
                    .send()
            } else {
                edtSongRequested.error = "This field cannot be empty."
            }

        }
    }
}


