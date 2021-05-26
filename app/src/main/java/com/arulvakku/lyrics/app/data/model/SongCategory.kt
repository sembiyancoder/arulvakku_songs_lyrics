package com.arulvakku.lyrics.app.data.model

data class SongCategory(
    val IsTransactionDone: Boolean,
    val LicensedBy: String,
    val Method: Any,
    val RequestUri: Any,
    val Result: List<CategoriesResult>,
    val StatusCode: Int,
    val Version: String
)