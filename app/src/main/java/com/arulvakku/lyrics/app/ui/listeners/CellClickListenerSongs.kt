package com.arulvakku.lyrics.app.ui.listeners


import com.arulvakku.lyrics.app.ui.view.home.song.SongModel

interface CellClickListenerSongs {
    fun onSongCellClickListener(item: SongModel, position: Int)
}