package com.arulvakku.lyrics.app.ui.view.favourite.cache

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["playlist_id", "sSongId"])
data class PlaylistSongCrossRef(
        @ColumnInfo(name = "playlist_id")
        val playlistId: Long,
        val sSongId: Long
)