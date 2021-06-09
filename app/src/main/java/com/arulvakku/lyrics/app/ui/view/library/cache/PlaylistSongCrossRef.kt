package com.arulvakku.lyrics.app.ui.view.library.cache

import androidx.room.Entity

@Entity(primaryKeys = ["playlistId", "sSongId"])
data class PlaylistSongCrossRef(
    val playlistId: Long,
    val sSongId: Long
)