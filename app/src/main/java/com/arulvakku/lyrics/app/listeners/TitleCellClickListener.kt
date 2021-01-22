package com.sembiyan.songs.app.listeners

import com.arulvakku.lyrics.app.data.Song

interface TitleCellClickListener {
    fun onTitleCellClickListener(position: Int, song: Song)
}