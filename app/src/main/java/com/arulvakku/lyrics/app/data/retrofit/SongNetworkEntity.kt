package com.arulvakku.lyrics.app.data.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SongNetworkEntity(
    @SerializedName("sSongId")
    @Expose
    val songId: Long,

    @SerializedName("sTitle")
    @Expose
    val title: String? = null,

    @SerializedName("sCategory")
    @Expose
    val category: String? = null,

    @SerializedName("sCategoryId")
    @Expose
    val categoryId: Long,

    @SerializedName("sSong")
    @Expose
    val song: String? = null
)
