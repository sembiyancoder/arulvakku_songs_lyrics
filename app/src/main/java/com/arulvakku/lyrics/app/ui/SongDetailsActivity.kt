package com.arulvakku.lyrics.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.arulvakku.lyrics.app.databinding.ActivitySongDetailsBinding
import com.arulvakku.lyrics.app.ui.lyrics.adapter.LyricsPagerAdapter
import com.arulvakku.lyrics.app.ui.home.song.SongModel
import com.arulvakku.lyrics.app.ui.lyrics.LyricsViewModel
import com.arulvakku.lyrics.app.utilities.Status
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SongDetailsActivity : AppCompatActivity() {

    private lateinit var pagerAdapter: LyricsPagerAdapter
    private lateinit var binding: ActivitySongDetailsBinding
    private lateinit var viewModel: LyricsViewModel
    private var songModel: SongModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        viewModel = ViewModelProvider(this).get(LyricsViewModel::class.java)
        songModel = intent.getSerializableExtra("song") as SongModel?


        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        songModel?.sCategoryId?.let { subscribe(it) }
    }

    private fun subscribe(categoryId: Int) {
        viewModel.getSongsListByCategory(categoryId);
        viewModel.songsResult.observe(this) { it ->
            when (it.status) {
                Status.LOADING -> {
                    Timber.d("loading...")
                }
                Status.SUCCESS -> {
                    Timber.d("success: ${it.data}")
                    setAdapter(it.data ?: emptyList())
                }
                Status.ERROR -> {
                    Timber.d("error: ${it.message}")
                }
            }
        }
    }

    private fun setAdapter(list: List<SongModel>) {
        pagerAdapter = LyricsPagerAdapter(supportFragmentManager, list)
        binding.viewPager.adapter = pagerAdapter

        binding.viewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
            }
        })
    }

}