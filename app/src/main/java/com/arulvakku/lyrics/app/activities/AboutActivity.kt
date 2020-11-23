package com.arulvakku.lyrics.app.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.arulvakku.lyrics.app.R
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        initActions()

    }

    private fun initActions() {
        nineth_one.setOnClickListener { openMail1() }
        fourth_one.setOnClickListener { openBrowser()  }
    }

    private fun openMail1() {
        val email = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", getString(R.string.email), null))
        startActivity(Intent.createChooser(email, getString(R.string.app_name)))
    }

    private fun openBrowser() {
        val uri = Uri.parse(getString(R.string.base_url))
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}