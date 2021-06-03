package com.arulvakku.lyrics.app.data.model

import com.arulvakku.lyrics.app.ui.home.song.network.networkentities.SongResult

data class Song(
    val IsTransactionDone: Boolean,
    val LicensedBy: String,
    val Method: Any,
    val RequestUri: Any,
    val Result: List<SongResult>,
    val StatusCode: Int,
    val Version: String
)