package com.arulvakku.lyrics.app.activities.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arulvakku.lyrics.app.R
import com.arulvakku.lyrics.app.activities.BaseActivity
import com.arulvakku.lyrics.app.activities.home.adapter.ViewPagerAdapter
import com.arulvakku.lyrics.app.activities.home.fragment.CategoriesFragment
import com.arulvakku.lyrics.app.activities.home.fragment.SongListFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setSupportActionBar(toolbar)

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(CategoriesFragment(), "பிரிவுகள்")
        adapter.addFragment(SongListFragment(), "பாடல்கள்")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

    }
}