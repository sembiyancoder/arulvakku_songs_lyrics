package com.arulvakku.lyrics.app.ui.view.library.cache

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Playlist(
    @PrimaryKey(autoGenerate = true) val playlistId: Int=0,
    val playlistName: String?=null
)