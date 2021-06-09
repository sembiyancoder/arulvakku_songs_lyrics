package com.example.many_to_many.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Playlist(
    @PrimaryKey(autoGenerate = true) val playlistId: Int=0,
    val playlistName: String?=null
)