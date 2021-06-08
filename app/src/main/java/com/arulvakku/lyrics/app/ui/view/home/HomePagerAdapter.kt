package com.arulvakku.lyrics.app.ui.view.home

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.arulvakku.lyrics.app.ui.view.home.category.CategoriesFragment
import com.arulvakku.lyrics.app.ui.view.home.song.SongsFragment
import com.arulvakku.lyrics.app.ui.view.songlist.SongListFragment

private val TAB_TITLES = arrayOf(
    "பிரிவுகள்",
    "பாடல்கள்"
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(fm: FragmentManager,behavior: Int) : FragmentPagerAdapter(fm, behavior) {

    override fun getItem(position: Int): Fragment {

        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).

        return when (position) {
            0 -> CategoriesFragment()
            1 -> SongsFragment()
            else -> CategoriesFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return TAB_TITLES[position]
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
}