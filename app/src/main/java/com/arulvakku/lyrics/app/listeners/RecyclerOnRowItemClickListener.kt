package com.sembiyan.songs.app.listeners

import com.arulvakku.lyrics.app.data.Song

interface RecyclerOnRowItemClickListener {
    fun onItemRowListener(position: Int, song: Song)
}