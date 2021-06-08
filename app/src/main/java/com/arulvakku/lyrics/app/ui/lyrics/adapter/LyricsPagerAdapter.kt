package com.arulvakku.lyrics.app.ui.lyrics.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.arulvakku.lyrics.app.ui.home.song.SongModel
import com.arulvakku.lyrics.app.ui.lyrics.LyricsFragment

class LyricsPagerAdapter(fragmentManager: FragmentManager, private val songs: List<SongModel>) :

    FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return LyricsFragment.newInstance(songs[position])
    }

    override fun getCount(): Int {
        return songs.size
    }
}