package com.arulvakku.lyrics.app.ui.home.category.network.networkentities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CategoriesResult(
    @SerializedName("sCategory")
    @Expose
    var sCategory: String?,

    @SerializedName("sCategoryId")
    @Expose
    var sCategoryId: Int,

    @SerializedName("sColorCode")
    @Expose
    var sColorCode: String?,

    @SerializedName("sCount")
    @Expose
    var sCount: Int?,
): Serializable