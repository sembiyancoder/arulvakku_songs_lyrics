package com.arulvakku.lyrics.app.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.arulvakku.lyrics.app.R
import com.arulvakku.lyrics.app.databinding.ActivityHomeBinding
import com.arulvakku.lyrics.app.utilities.Resource
import com.bosco.mrroom.utils.MainStateEvent
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    private val viewModel: SongViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        subscribeObservers()
        setState()
    }


    private fun subscribeObservers() {
        viewModel.dataStateSongs.observe(this, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    Timber.d("loading...")
                }
                Resource.Status.SUCCESS -> {
                    Timber.d("success: ${it.data}")
                }
                Resource.Status.ERROR -> {
                    Timber.d("error: ${it.message}")
                }
            }
        })
    }

    private fun setState() {
        viewModel.setStateSongs(MainStateEvent.GetBlogsEvent)
    }
}