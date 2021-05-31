package com.arulvakku.lyrics.app.ui.view.download

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.arulvakku.lyrics.app.databinding.ActivityLoadingBinding
import com.arulvakku.lyrics.app.ui.view.MainActivity
import com.arulvakku.lyrics.app.ui.view.home.category.network.SongCategoryNetworkEntity
import com.arulvakku.lyrics.app.ui.view.home.song.network.SongNetworkEntity
import com.arulvakku.lyrics.app.utilities.PreferenceStorage
import com.arulvakku.lyrics.app.utilities.Status
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class CacheActivity : AppCompatActivity() {

    val TAG = "CacheActivity"

    private val loginViewModel: CacheViewModel by viewModels()
    private lateinit var binding: ActivityLoadingBinding

    @Inject
    lateinit var prefStorage: PreferenceStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root) // returns view

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

    private fun renderSongCategories(categoryResult: SongCategoryNetworkEntity) {
        lifecycleScope.launch {
            val gson = Gson()
            val json: String = gson.toJson(categoryResult)
            prefStorage.setSongCategory(json)
        }
    }

    private fun renderSongs(songResult: SongNetworkEntity) {

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
        binding.progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }


}