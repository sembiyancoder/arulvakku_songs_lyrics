package com.example.many_to_many.data.room.entities

import androidx.room.Entity

@Entity(primaryKeys = ["playlistId", "sSongId"])
data class PlaylistSongCrossRef(
    val playlistId: Long,
    val sSongId: Long
)