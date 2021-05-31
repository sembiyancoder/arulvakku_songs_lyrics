package com.arulvakku.lyrics.app.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.arulvakku.lyrics.app.data.model.SongResult
import com.arulvakku.lyrics.app.ui.view.lyrics.LyricsFragment

class LyricsPagerAdapter(fragmentManager: FragmentManager, private val songs: List<SongResult>) :

    FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return LyricsFragment.newInstance()
    }

    override fun getCount(): Int {
        return songs.size
    }
}