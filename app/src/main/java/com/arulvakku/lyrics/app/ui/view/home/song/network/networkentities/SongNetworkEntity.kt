package com.arulvakku.lyrics.app.ui.view.home.song.network.networkentities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SongNetworkEntity(
    @SerializedName("IsTransactionDone")
    @Expose
    val IsTransactionDone: Boolean,
    @SerializedName("LicensedBy")
    @Expose
    val LicensedBy: String,
    @SerializedName("Method")
    @Expose
    val Method: Any,
    @SerializedName("RequestUri")
    @Expose
    val RequestUri: Any,
    @SerializedName("Result")
    @Expose
    val Result: List<SongResult>,
    @SerializedName("StatusCode")
    @Expose
    val StatusCode: Int,
    @SerializedName("Version")
    @Expose
    val Version: String
)
