package com.arulvakku.lyrics.app.activities.home

import android.app.UiModeManager
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.arulvakku.lyrics.app.R
import com.arulvakku.lyrics.app.activities.BaseActivity
import com.arulvakku.lyrics.app.activities.home.adapter.ViewPagerAdapter
import com.arulvakku.lyrics.app.activities.home.fragment.CategoriesFragment
import com.arulvakku.lyrics.app.activities.home.fragment.SongListFragment
import com.arulvakku.lyrics.app.databinding.ActivityHomeBinding
import com.arulvakku.lyrics.app.utilities.Constants
import com.arulvakku.lyrics.app.utilities.Prefs
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : BaseActivity() {

    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setting false to change splash screen page loding settings
        Prefs.putBoolean(Constants.IS_FIRST_TIME, false)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        binding.toolbar.setTitleTextColor(resources.getColor(R.color.tabIndicatorColor))
        setSupportActionBar(toolbar)

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(CategoriesFragment(), "பிரிவுகள்")
        adapter.addFragment(SongListFragment(), "பாடல்கள்")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)


    }


}