package com.arulvakku.lyrics.app.activities.lyrics.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.arulvakku.lyrics.app.activities.lyrics.fragment.LyricsFragment
import com.arulvakku.lyrics.app.data.Song

class LyricsPagerAdapter(fragmentManager: FragmentManager, private val songs: List<Song>) :

    FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return LyricsFragment.newInstance(songs[position])
    }

    override fun getCount(): Int {
        return songs.size
    }
}