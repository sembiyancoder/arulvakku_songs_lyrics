package com.arulvakku.lyrics.app.ui.view.favourite.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "playlist")
data class Playlist(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "playlist_id")
    val playlistId: Int=0,

    @ColumnInfo(name="playlist_name")
    val playlistName: String?=null
)