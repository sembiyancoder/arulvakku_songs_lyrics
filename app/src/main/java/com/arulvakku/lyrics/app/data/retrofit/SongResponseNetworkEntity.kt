package com.arulvakku.lyrics.app.data.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SongResponseNetworkEntity(
    @SerializedName("Result")
    @Expose
    val result: List<SongNetworkEntity>? = null
)
