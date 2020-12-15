package com.arulvakku.lyrics.app.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.arulvakku.lyrics.app.R
import com.arulvakku.lyrics.app.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    lateinit var binding: ActivityAboutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initActions()
    }

    private fun initActions() {
        binding.txtEmail.setOnClickListener { openMail1() }
        binding.txtWebsite.setOnClickListener { openBrowser() }
    }

    private fun openMail1() {
        val email =
            Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", getString(R.string.email), null))
        startActivity(Intent.createChooser(email, getString(R.string.app_name)))
    }

    private fun openBrowser() {
        val uri = Uri.parse(getString(R.string.base_url))
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}