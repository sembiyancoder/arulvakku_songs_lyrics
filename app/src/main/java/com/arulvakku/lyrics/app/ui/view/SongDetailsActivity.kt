package com.arulvakku.lyrics.app.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.arulvakku.lyrics.app.R
import com.arulvakku.lyrics.app.data.model.Song
import com.arulvakku.lyrics.app.databinding.ActivitySongDetailsBinding
import com.arulvakku.lyrics.app.ui.adapters.LyricsPagerAdapter
import com.arulvakku.lyrics.app.ui.viewmodels.LyricsViewModel
import com.arulvakku.lyrics.app.ui.viewmodels.SongsViewModel

class SongDetailsActivity : AppCompatActivity() {

    private lateinit var pagerAdapter: LyricsPagerAdapter
    private lateinit var binding: ActivitySongDetailsBinding
    private lateinit var viewModel: LyricsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(LyricsViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()

        pagerAdapter = LyricsPagerAdapter(supportFragmentManager,  viewModel.songsResult.Result)
        binding.viewPager.adapter = pagerAdapter

        binding.viewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }
            override fun onPageSelected(position: Int) {
            }
        })
    }
}