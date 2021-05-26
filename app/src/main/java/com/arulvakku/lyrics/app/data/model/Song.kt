package com.arulvakku.lyrics.app.data.model

data class Song(
    val IsTransactionDone: Boolean,
    val LicensedBy: String,
    val Method: Any,
    val RequestUri: Any,
    val Result: List<SongResult>,
    val StatusCode: Int,
    val Version: String
)