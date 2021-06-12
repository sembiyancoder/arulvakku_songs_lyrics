package com.arulvakku.lyrics.app.ui.view.home.song

import java.io.Serializable

data class SongModel(
    var sSongId: Int?,
    var sCategory: String?,
    var sCategoryId: Int?,
    var sSong: String?,
    var sTitle: String?,
): Serializable{
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}