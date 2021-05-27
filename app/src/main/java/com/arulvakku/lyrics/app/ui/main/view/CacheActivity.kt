package com.arulvakku.lyrics.app.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.arulvakku.lyrics.app.R
import com.arulvakku.lyrics.app.data.model.Song
import com.arulvakku.lyrics.app.data.model.SongCategory
import com.arulvakku.lyrics.app.ui.main.viewmodels.CacheViewModel
import com.arulvakku.lyrics.app.utilities.PreferenceStorage
import com.arulvakku.lyrics.app.utilities.Status
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class CacheActivity : AppCompatActivity() {

    val TAG = "CacheActivity"

    private val loginViewModel: CacheViewModel by viewModels()
    private lateinit var progressBar: ProgressBar

    @Inject
    lateinit var prefStorage: PreferenceStorage

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
        lifecycleScope.launch {
            val gson = Gson()
            val json: String = gson.toJson(categoryResult)
            prefStorage.setSongCategory(json)
        }
    }

    private fun renderSongs(songResult: Song) {

        lifecycleScope.launch {
            val gson = Gson()
            val json: String = gson.toJson(songResult)
            prefStorage.setSong(json)
        }



        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }


}