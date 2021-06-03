package com.arulvakku.lyrics.app.ui.home.song.network.networkentities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SongResult(
    @SerializedName("sCategory")
    @Expose
    var sCategory: String?,

    @SerializedName("sCategoryId")
    @Expose
    var sCategoryId: Int?,

    @SerializedName("sSong")
    @Expose
    var sSong: String?,

    @SerializedName("sSongId")
    @Expose
    var sSongId: Int?,

    @SerializedName("sTitle")
    @Expose
    var sTitle: String?,
):Serializable