package com.arulvakku.lyrics.app.activities.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.arulvakku.lyrics.app.activities.home.adapter.ViewPagerAdapter
import com.arulvakku.lyrics.app.activities.home.favorite.FavoriteFragment
import com.arulvakku.lyrics.app.activities.home.search.SearchFragment
import com.arulvakku.lyrics.app.activities.home.setting.Settingsragment
import com.arulvakku.lyrics.app.activities.home.song.HomeFragment
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

        setUpViewPagerAdapter()
    }

    /*
    * Setting up the tabs
    * */
    private fun setUpViewPagerAdapter() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(HomeFragment(), "பிரிவுகள்")
        adapter.addFragment(SearchFragment(), "பாடல்கள்")
        adapter.addFragment(FavoriteFragment(), "பிடித்தது")
        adapter.addFragment(Settingsragment(), "பட்டியல்")
        binding.viewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
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