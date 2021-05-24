package com.arulvakku.lyrics.app.ui.song.service.networkentity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Data class to have songs
 */
data class SongResponseNetworkEntity(
    @SerializedName("Result")
    @Expose
    val result: List<SongNetworkEntity>? = null
)
