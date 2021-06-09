package com.arulvakku.lyrics.app.ui.view.library.cache

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.arulvakku.lyrics.app.ui.view.home.song.cache.SongCacheEntity

data class PlaylistWithSongs(
    @Embedded val playlist: Playlist,
    @Relation(
        parentColumn = "playlistId",
        entityColumn = "sSongId",
        associateBy = Junction(PlaylistSongCrossRef::class)
    )
    val songs: List<SongCacheEntity>?=null
)