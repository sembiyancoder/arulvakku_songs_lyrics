package com.arulvakku.lyrics.app.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.arulvakku.lyrics.app.R
import com.arulvakku.lyrics.app.data.model.Song
import com.arulvakku.lyrics.app.data.model.SongCategory
import com.arulvakku.lyrics.app.ui.main.viewmodels.CacheViewModel
import com.arulvakku.lyrics.app.utilities.Status
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CacheActivity : AppCompatActivity() {

    val TAG = "CacheActivity"

    private val loginViewModel: CacheViewModel by viewModels()
    private lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)
        progressBar = findViewById(R.id.progressBar);
        setupSongCategoriesObserver()
        setupSongObserver()

    }

    private fun setupSongCategoriesObserver() {
        loginViewModel.songCategoriesResult.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { categoryResult -> renderSongCategories(categoryResult) }
                }
                Status.LOADING -> {

                }
                Status.ERROR -> {
                    //Handle Error
                }
            }
        })
    }

    private fun setupSongObserver() {
        loginViewModel.songsResult.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    hideProgressBar()
                    it.data?.let { songResult -> renderSongs(songResult) }
                }
                Status.LOADING -> {
                    showProgressBar()
                }
                Status.ERROR -> {
                    hideProgressBar()
                }
            }
        })
    }

    private fun renderSongCategories(categoryResult: SongCategory) {

    }

    private fun renderSongs(songResult: Song) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }


}