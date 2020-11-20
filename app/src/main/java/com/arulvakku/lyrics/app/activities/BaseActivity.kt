package com.arulvakku.lyrics.app.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.arulvakku.lyrics.app.R


open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId
        return when (id) {
            R.id.about -> {
                startActivity(Intent(applicationContext, AboutActivity::class.java))
                true
            }
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.feedback -> {
                openMail()
                true
            }
            R.id.search -> {
                startActivity(Intent(applicationContext, SongSearchActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun openMail() {
        val email =
            Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", getString(R.string.email), null))
        startActivity(Intent.createChooser(email, getString(R.string.app_name)))
    }

}