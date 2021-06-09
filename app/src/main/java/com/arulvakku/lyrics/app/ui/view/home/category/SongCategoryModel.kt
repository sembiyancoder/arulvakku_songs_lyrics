package com.arulvakku.lyrics.app.ui.view.home.category

import java.io.Serializable


data class SongCategoryModel (
    var sCategory: String?,
    var sCategoryId: Int,
    var sColorCode: String?,
    var sCount: Int?,
):Serializable