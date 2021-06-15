package com.arulvakku.lyrics.app.ui.view.download

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.arulvakku.lyrics.app.databinding.ActivityLoadingBinding
import com.arulvakku.lyrics.app.ui.view.MainActivity
import com.arulvakku.lyrics.app.utilities.Status
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CacheActivity : AppCompatActivity() {

    private val loginViewModel: CacheViewModel by viewModels()
    private lateinit var binding: ActivityLoadingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root) // returns view

        setupSongCategoriesObserver()
        setupSongObserver()

    }

    private fun setupSongCategoriesObserver() {
        loginViewModel.songCategoriesResult.observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let {
                    }
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
        loginViewModel.songsResult.observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    hideProgressBar()
                    startMainActivity()
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


    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
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