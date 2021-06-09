package com.arulvakku.lyrics.app.ui.view.library.cache

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.arulvakku.lyrics.app.ui.view.home.song.cache.SongCacheEntity
import com.arulvakku.lyrics.app.ui.view.library.cache.Playlist

data class SongWithPlaylists(
    @Embedded val song: SongCacheEntity,
    @Relation(
        parentColumn = "sSongId",
        entityColumn = "playlistId",
        associateBy = Junction(PlaylistSongCrossRef::class)
    )
    val playlists: List<Playlist>? = null
)