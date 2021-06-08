package com.example.many_to_many.data.room.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.arulvakku.lyrics.app.ui.view.home.song.cache.SongCacheEntity

data class SongWithPlaylists(
    @Embedded val song: SongCacheEntity,
    @Relation(
        parentColumn = "sSongId",
        entityColumn = "playlistId",
        associateBy = Junction(PlaylistSongCrossRef::class)
    )
    val playlists: List<Playlist>?=null
)